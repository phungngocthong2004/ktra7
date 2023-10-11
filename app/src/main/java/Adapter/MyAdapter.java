package Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import Fragmnet.FragmnetChuot;
import Fragmnet.FragmnetThongtin;

public class MyAdapter extends FragmentStateAdapter {
    public MyAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new FragmnetChuot();
            default:
                return new FragmnetThongtin();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
