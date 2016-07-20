/***/
package org.eclipse.ui.examples.propertysheet;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.texteditor.IDocumentProvider;

/**
* This class is an example of the implementation of a simple parser.
*/
public class UserFileParser {

    /**
* Return the fabricated result for this example.
*
*/
    private static IAdaptable getFabricatedResult() {
        // returns fabricated input.
        GroupElement root = new GroupElement(MessageUtil.getString("Everybody"), //$NON-NLS-1$
        null);
        GroupElement userGroup = root.createSubGroup(MessageUtil.getString(//$NON-NLS-1$
        "Company_Inc"));
        GroupElement ottGroup = userGroup.createSubGroup(MessageUtil.getString(//$NON-NLS-1$
        "Waterloo_Lab"));
        //$NON-NLS-1$
        userGroup.createSubGroup(MessageUtil.getString("Toronto_Lab"));
        //$NON-NLS-1$
        userGroup.createSubGroup(MessageUtil.getString("Hamilton_Lab"));
        //$NON-NLS-1$
        userGroup.createSubGroup(MessageUtil.getString("London_Lab"));
        //$NON-NLS-1$
        userGroup.createSubGroup(MessageUtil.getString("Grimsby_Lab"));
        GroupElement uiTeam = ottGroup.createSubGroup(MessageUtil.getString(//$NON-NLS-1$
        "Team1"));
        //
        //$NON-NLS-1$
        UserElement user1 = uiTeam.createUser("richard");
        //$NON-NLS-1$
        user1.setFullName(new Name(MessageUtil.getString("Richard_Zokol")));
        user1.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "rzokol@company.com")));
        //$NON-NLS-1$
        user1.setPhoneNumber("x789");
        user1.setAddress(new Address(new StreetAddress(232, MessageUtil.getString("Champlain")), MessageUtil.getString("Hull"), Integer.valueOf(5), //$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        user1.setBirthday(new Birthday(18, 1, 1981));
        user1.setCoop(Boolean.TRUE);
        user1.setHairColor(new RGB(0, 0, 0));
        user1.setEyeColor(new RGB(0, 0, 0));
        //
        //$NON-NLS-1$
        UserElement user2 = uiTeam.createUser("george");
        //$NON-NLS-1$
        user2.setFullName(new Name(MessageUtil.getString("George_Knudson")));
        user2.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "gknudson@company.com")));
        //$NON-NLS-1$
        user2.setPhoneNumber("x678");
        user2.setAddress(new Address(new StreetAddress(), MessageUtil.getString("Toronto"), Integer.valueOf(4), //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        user2.setBirthday(new Birthday(7, 5, 1978));
        user2.setCoop(Boolean.TRUE);
        user2.setHairColor(new RGB(0, 0, 0));
        user2.setEyeColor(new RGB(0, 0, 0));
        //
        //$NON-NLS-1$
        UserElement user3 = uiTeam.createUser("arnold");
        //$NON-NLS-1$
        user3.setFullName(new Name(MessageUtil.getString("Arnold_Palmer")));
        user3.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "apalmer@company.com")));
        //$NON-NLS-1$
        user3.setPhoneNumber("x567");
        user3.setAddress(new Address(new StreetAddress(), MessageUtil.getString("Ottawa"), Integer.valueOf(4), //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        user3.setBirthday(new Birthday(11, 23, 1962));
        user3.setHairColor(new RGB(0, 0, 0));
        user3.setEyeColor(new RGB(0, 0, 0));
        //
        //$NON-NLS-1$
        UserElement user4 = uiTeam.createUser("lee");
        //$NON-NLS-1$
        user4.setFullName(new Name(MessageUtil.getString("Lee_Trevino")));
        user4.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "ltrevino@company.com")));
        //$NON-NLS-1$
        user4.setPhoneNumber("x456");
        user4.setAddress(new Address(new StreetAddress(), MessageUtil.getString("Ottawa"), Integer.valueOf(4), //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        //
        //$NON-NLS-1$
        UserElement user5 = uiTeam.createUser("tiger");
        //$NON-NLS-1$
        user5.setFullName(new Name(MessageUtil.getString("Tiger_Woods")));
        user5.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "twoods@company.com")));
        //$NON-NLS-1$
        user5.setPhoneNumber("x345");
        user5.setAddress(new Address(new StreetAddress(), MessageUtil.getString("Ottawa"), Integer.valueOf(4), //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        //
        //$NON-NLS-1$
        UserElement user6 = uiTeam.createUser("jack");
        //$NON-NLS-1$
        user6.setFullName(new Name(MessageUtil.getString("Jack_Nicklaus")));
        user6.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "jnicklaus@company.com")));
        //$NON-NLS-1$
        user6.setPhoneNumber("x234 ");
        user6.setAddress(new Address(new StreetAddress(), MessageUtil.getString("Ottawa"), Integer.valueOf(4), //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        //
        //$NON-NLS-1$
        UserElement greg = uiTeam.createUser("weslock");
        //$NON-NLS-1$
        greg.setFullName(new Name(MessageUtil.getString("Weslock")));
        greg.setEmailAddress(new EmailAddress(MessageUtil.getString(//$NON-NLS-1$
        "weslock@company.com")));
        //$NON-NLS-1$
        greg.setPhoneNumber("x123");
        greg.setAddress(new Address(new StreetAddress(), MessageUtil.getString("Ottawa"), Integer.valueOf(4), //$NON-NLS-2$ //$NON-NLS-1$
        MessageUtil.getString("A1B2C3")));
        return root;
    }

    /**
* Parse the input given by the argument. For this example we do no parsing and return
* a fabricated result.
*
*/
    public IAdaptable parse(IDocumentProvider documentProvider) {
        return getFabricatedResult();
    }
}
