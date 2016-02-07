package bank.two.reporting_visitor;

import java.util.List;

public interface ReportingVisitor {
    List<ReportingObject> visit(ReportingObject object);
}
