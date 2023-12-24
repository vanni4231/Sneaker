package com.project.SneakerShip.views;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.SneakerShip.R;
import com.project.SneakerShip.utils.adapter.ShoeItemAdapter;
import com.project.SneakerShip.utils.model.ShoeCart;
import com.project.SneakerShip.utils.model.ShoeItem;
import com.project.SneakerShip.viewmodel.CartViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShoeItemAdapter.ShoeClickedListeners {
    public BottomNavigationView bottomNavigationView;
    RecyclerView recyclerView;
    private List<ShoeItem> shoeItemList;
    ShoeItemAdapter adapter;
    private CartViewModel viewModel;
    private List<ShoeCart> shoeCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    private SearchView searchView;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search_view);
        textView = findViewById(R.id.textView);
        searchView.clearFocus();
        searchView = findViewById(R.id.search_view);
        textView = findViewById(R.id.textView);
        // ...

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                textView.setVisibility(View.VISIBLE);
                return false;
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }

            private void filterList(String text) {
                List<ShoeItem> filteredList = new ArrayList<>();
                for (ShoeItem item : shoeItemList) {
                    if (item.getShoeBrandName().toLowerCase().contains(text.toLowerCase())) {
                        filteredList.add(item);
                    }

                }
                if (filteredList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.setFilteredList(filteredList);
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.person);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.person:
                        return true;

                }
                return false;
            }

        });

        initializeVariables();
        setUpList();


        adapter.setShoeItemList(shoeItemList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });
    }

    public void setUpList() {
        shoeItemList.add(new ShoeItem("Nike Revolution", "Nike", R.drawable.nike_revolution_road, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Revolution", "Nike", R.drawable.nike_revolution_road, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Revolution", "Nike", R.drawable.nike_revolution_road, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Revolution", "Nike", R.drawable.nike_revolution_road, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Revolution", "Nike", R.drawable.nike_revolution_road, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));
        shoeItemList.add(new ShoeItem("Nike Flex Run 2021", "NIKE", R.drawable.flex_run_road_running, 20));
        shoeItemList.add(new ShoeItem("Court Zoom Vapor", "NIKE", R.drawable.nikecourt_zoom_vapor_cage, 18));
        shoeItemList.add(new ShoeItem("EQ21 Run COLD.RDY", "ADIDAS", R.drawable.adidas_eq_run, 16.5));
        shoeItemList.add(new ShoeItem("Adidas Ozelia", "ADIDAS", R.drawable.adidas_ozelia_shoes_grey, 20));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 22));
        shoeItemList.add(new ShoeItem("Adidas Questar", "ADIDAS", R.drawable.adidas_questar_shoes, 12));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "ADIDAS", R.drawable.adidas_ultraboost, 15));


    }

    public void initializeVariables() {

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        shoeCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        shoeItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ShoeItemAdapter(this);
    }


    @Override
    public void onCardClicked(ShoeItem shoe) {
        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("shoeItem", shoe);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(ShoeItem shoeItem) {
        ShoeCart shoeCart = new ShoeCart();
        shoeCart.setShoeName(shoeItem.getShoeName());
        shoeCart.setShoeBrandName(shoeItem.getShoeBrandName());
        shoeCart.setShoePrice(shoeItem.getShoePrice());
        shoeCart.setShoeImage(shoeItem.getShoeImage());

        final int[] quantity = {1};
        final int[] id = new int[1];

        if (!shoeCartList.isEmpty()) {
            for (int i = 0; i < shoeCartList.size(); i++) {
                if (shoeCart.getShoeName().equals(shoeCartList.get(i).getShoeName())) {
                    quantity[0] = shoeCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = shoeCartList.get(i).getId();
                }
            }
        }

        Log.d("TAG", "onAddToCartBtnClicked: " + quantity[0]);

        if (quantity[0] == 1) {
            shoeCart.setQuantity(quantity[0]);
            shoeCart.setTotalItemPrice(quantity[0] * shoeCart.getShoePrice());
            viewModel.insertCartItem(shoeCart);
        } else {
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0] * shoeCart.getShoePrice());
        }

        makeSnackBar("Item Added To Cart");
    }

    public void makeSnackBar(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }



}

