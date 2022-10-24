package com.example.taller11tab;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.HashMap;
import java.util.Map;

class ViewPagerAdapter extends FragmentStateAdapter {

  enum Tab {

    HOGAR(0, R.string.tab_hogar, R.drawable.img),
    FAVORITOS(1, R.string.tab_favoritos, R.drawable.img_1),
    MUSICA(2, R.string.tab_musica, R.drawable.img_2),
    PELICULAS(3, R.string.tab_peliculas, R.drawable.img_3);
    final int title;
    final int icon;
    final int position;

    Tab(int position, @StringRes int title, @DrawableRes int icon) {
      this.position = position;
      this.title = title;
      this.icon = icon;
    }

    private static final Map<Integer,Tab> map;
    static {
      map = new HashMap<>();
      for (Tab t : Tab.values()) {
        map.put(t.position, t);
      }
    }

    static Tab byPosition(int position) {
      return map.get(position);
    }
  }

  public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
    super(fragmentManager, lifecycle);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
    if (position == Tab.HOGAR.position)
      return TabNameFragment.newInstance(Tab.HOGAR.title);
    else if (position == Tab.FAVORITOS.position)
      return TabNameFragment.newInstance(Tab.FAVORITOS.title);
    else if (position == Tab.MUSICA.position)
      return TabNameFragment.newInstance(Tab.MUSICA.title);
    else if (position == Tab.PELICULAS.position)
      return new RecyclerViewFragment();
    else
      throw new IllegalArgumentException("POSICION DESCONOCIDA " + position);
  }

  @Override
  public int getItemCount() {
    return Tab.values().length;
  }
}
