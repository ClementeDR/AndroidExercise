package clemente.activitytask;

import android.content.Intent;

public class ActivityA extends BaseActivity  {

    @Override
    protected String getName() {
        return "A";
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
