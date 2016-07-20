/***/
package org.eclipse.ui.examples.jobs;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
* TestJobRule is a scheduling rules that makes all jobs sequential.
*
*/
public class TestJobRule implements ISchedulingRule {

    private int jobOrder;

    public  TestJobRule(int order) {
        jobOrder = order;
    }

    @Override
    public boolean contains(ISchedulingRule rule) {
        if (rule instanceof IResource || rule instanceof TestJobRule)
            return true;
        return false;
    }

    @Override
    public boolean isConflicting(ISchedulingRule rule) {
        if (!(rule instanceof TestJobRule))
            return false;
        return ((TestJobRule) rule).getJobOrder() >= jobOrder;
    }

    /**
* Return the order of this rule.
* @return
*/
    public int getJobOrder() {
        return jobOrder;
    }
}
