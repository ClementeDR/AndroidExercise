package clemente.activitytask;

import android.content.Intent;

public class ActivityB extends BaseActivity {

    @Override
    protected String getName() {
        return "B";
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }
}
