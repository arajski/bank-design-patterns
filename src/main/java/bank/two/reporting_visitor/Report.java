package bank.two.reporting_visitor;

import java.util.ArrayList;
import java.util.List;

public class Report implements ReportingVisitor {

    private List<ReportingObject> objectsHistory = new ArrayList<ReportingObject>();

    @Override
    public List<ReportingObject> visit(ReportingObject object) {
        objectsHistory.add(object);
        return objectsHistory;
    }

    public List<ReportingObject> listOfObjectsWithValueLowerThan(int value) {
        List<ReportingObject> responseList = new ArrayList<ReportingObject>();
        for (ReportingObject object : objectsHistory) {
            if(object.objectValue() < value) {
                responseList.add(object);
            }
        }
        return responseList;
    }

    public List<ReportingObject> listOfObjectsWithValueGreaterThan(int value) {
        List<ReportingObject> responseList = new ArrayList<ReportingObject>();
        for (ReportingObject object : objectsHistory) {
            if(object.objectValue() > value) {
                responseList.add(object);
            }
        }
        return responseList;
    }

    public List<ReportingObject> listOfObjectsWithValueEqualTo(int value) {
        List<ReportingObject> responseList = new ArrayList<ReportingObject>();
        for (ReportingObject object : objectsHistory) {
            if(object.objectValue() == value) {
                responseList.add(object);
            }
        }
        return responseList;
    }
}
