package tw.itlab.zhaojun.o2o.unicorn_ui_v4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by zhaojun on 15/12/29.
 */
public class Tab_Apdater extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Tab_Apdater(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_Local tab1 = new Tab_Local();
                return tab1;
            case 1:
                Tab_Hot tab2 = new Tab_Hot();
                return tab2;
            case 2:
                Tab_Today tab3 = new Tab_Today();
                return tab3;
            case 3:
                Tab_Period tab4 = new Tab_Period();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

