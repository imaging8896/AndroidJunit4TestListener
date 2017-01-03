package imaging.android.test;

import android.os.Environment;
import android.support.test.internal.runner.listener.InstrumentationRunListener;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.File;

import imaging.android.test.annotation.Bug;

public class JUnitResultFormatterAsRunListener extends InstrumentationRunListener {

    private static final String reportPath = Environment.getExternalStorageDirectory().getPath() + File.separator + "report";
    private static final String reportFilePath = reportPath + File.separator + "report";


    private final XmlReportFormatter formatter;

    public JUnitResultFormatterAsRunListener() {
        File reportDir = new File(reportPath);
        if(!reportDir.exists()) {
            if(!reportDir.mkdir())
                throw new RuntimeException("Unable to mkdir for test result.");
        }

        formatter = new XmlReportFormatter(reportPath);
    }

    @Override
    public void testRunStarted(Description description) throws Exception {
        for(Description childDesc : description.getChildren())
            formatter.startTestSuite(childDesc.getClassName());
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        formatter.endAllTestSuite();
    }

    @Override
    public void testStarted(Description description) throws Exception {
        formatter.startTest(description);
    }

    @Override
    public void testFinished(Description description) throws Exception {
        formatter.endTest(description);
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        formatter.addFailure(failure, failure.getException());
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        formatter.addFailure(failure, failure.getException());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        if(description.getAnnotation(Bug.class) != null)
            formatter.addKnownBug(description);
        else
            formatter.addIgnored(description);
    }
}
