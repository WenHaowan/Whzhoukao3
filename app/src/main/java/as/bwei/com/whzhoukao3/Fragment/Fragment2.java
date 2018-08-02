package as.bwei.com.whzhoukao3.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import as.bwei.com.whzhoukao3.R;

/**
 * Created by HP on 2018/7/23.
 */

public class Fragment2 extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment2, null);
        return view;
    }
}
