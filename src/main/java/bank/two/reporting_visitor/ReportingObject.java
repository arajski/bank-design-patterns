package bank.two.reporting_visitor;

public interface ReportingObject {
    int objectValue();
    void addToVisitor(ReportingVisitor visitor);
}
