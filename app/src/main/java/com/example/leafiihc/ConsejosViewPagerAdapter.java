package com.example.leafiihc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ConsejosViewPagerAdapter extends FragmentStateAdapter {

    public ConsejosViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ConsejosFragment();
            case 1:
                return new CalendarioFragment();
            case 2:
                return new ComunidadFragment();
            default:
                return new ConsejosFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Número de pestañas
    }
}