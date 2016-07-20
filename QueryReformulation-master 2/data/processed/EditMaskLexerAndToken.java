/***/
package org.eclipse.jface.examples.databinding.mask.internal;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.examples.databinding.mask.EditMaskParseException;

/**
* Lexical analyzer and token for an input mask.  Since input masks have exactly
* one token type, we use the same class to be the recognizer and the token
* itself.
*
* @since 3.3
*/
public class EditMaskLexerAndToken {

    /*
* First the literals that represent the types of characters
*/
    private static List<String> reservedWords = new ArrayList();

    {
        reservedWords.add("#");
        reservedWords.add("A");
        reservedWords.add("a");
        reservedWords.add("n");
    }

    /*
* ...and their corresponding regular expressions
*/
    private static List<String> inputRegexes = new ArrayList();

    {
        inputRegexes.add("^[0-9]$");
        inputRegexes.add("^[A-Z]$");
        inputRegexes.add("^[a-zA-Z]$");
        inputRegexes.add("^[0-9a-zA-Z]$");
    }

    // A regex for matching input characters or null
    private String charRegex = null;

    // The literal character if charRegex is null
    private String literal = null;

    private boolean readOnly;

    // The user's input
    private String input = null;

    private boolean recognizeReservedWord(String inputMask, int position) {
        String input = inputMask.substring(position, position + 1);
        for (int reservedWord = 0; reservedWord < reservedWords.size(); reservedWord++) {
            if (input.equals(reservedWords.get(reservedWord))) {
                charRegex = inputRegexes.get(reservedWord);
                literal = null;
                input = null;
                readOnly = false;
                return true;
            }
        }
        return false;
    }

    private boolean recognizeBackslashLiteral(String inputMask, int position) throws EditMaskParseException {
        String input = inputMask.substring(position, position + 1);
        if (input.equals("\\")) {
            try {
                input = inputMask.substring(position + 1, position + 2);
                charRegex = null;
                this.input = input;
                literal = input;
                readOnly = true;
                return true;
            } catch (Throwable t) {
                throw new EditMaskParseException("Found a \\ without a character after it: " + inputMask);
            }
        }
        return false;
    }

    private boolean recognizeLiteral(String inputMask, int position) {
        literal = inputMask.substring(position, position + 1);
        this.input = literal;
        charRegex = null;
        readOnly = true;
        return true;
    }

    public int initializeEditMask(String inputMask, int position) throws EditMaskParseException {
        clear();
        if (recognizeReservedWord(inputMask, position)) {
            return 1;
        }
        if (recognizeBackslashLiteral(inputMask, position)) {
            return 2;
        }
        if (!recognizeLiteral(inputMask, position)) {
            throw new EditMaskParseException("Should never see this error in this implementation!");
        }
        readOnly = true;
        return 1;
    }

    public boolean accept(String inputCharacter) {
        if (readOnly) {
            return false;
        }
        if (literal != null) {
            return false;
        }
        if (!canAcceptMoreCharacters()) {
            return false;
        }
        if (inputCharacter.matches(charRegex)) {
            this.input = inputCharacter;
            return true;
        }
        return false;
    }

    public String getInput() {
        return input;
    }

    public void clear() {
        if (!isReadOnly())
            input = null;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public boolean isComplete() {
        if (input != null) {
            return true;
        }
        return false;
    }

    public boolean canAcceptMoreCharacters() {
        return !isComplete();
    }

    public int getMinimumLength() {
        return 1;
    }
}
