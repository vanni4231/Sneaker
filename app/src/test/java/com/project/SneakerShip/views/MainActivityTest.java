package com.project.SneakerShip.views;

import android.content.Intent;
import android.widget.SearchView;
import android.widget.TextView;

import com.project.SneakerShip.R;
import com.project.SneakerShip.utils.adapter.ShoeItemAdapter;
import com.project.SneakerShip.utils.model.ShoeCart;
import com.project.SneakerShip.utils.model.ShoeItem;
import com.project.SneakerShip.viewmodel.CartViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    private BottomNavigationView bottomNavigationView;

    @Mock
    private RecyclerView recyclerView;

    @Mock
    private ShoeItemAdapter adapter;

    @Mock
    private CartViewModel viewModel;

    @Mock
    private Snackbar snackbar;

    @Mock
    private CoordinatorLayout coordinatorLayout;

    @InjectMocks
    private MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivity = spy(new MainActivity());
        doNothing().when(mainActivity).initializeVariables();
        doNothing().when(mainActivity).setUpList();
        doNothing().when(mainActivity).makeSnackBar(anyString());
        doNothing().when(mainActivity).setContentView(anyInt());
        doNothing().when(mainActivity).onCreate(any());
        when(mainActivity.findViewById(eq(R.id.bottomNavigationView))).thenReturn(bottomNavigationView);
        when(mainActivity.findViewById(eq(R.id.mainRecyclerView))).thenReturn(recyclerView);
        when(mainActivity.findViewById(eq(R.id.coordinatorLayout))).thenReturn(coordinatorLayout);
        when(mainActivity.findViewById(eq(R.id.search_view))).thenReturn(mock(SearchView.class));
        when(mainActivity.findViewById(eq(R.id.textView))).thenReturn(mock(TextView.class));
    }

    @Test
    public void testOnCreate() {
        mainActivity.onCreate(null);
        verify(mainActivity).initializeVariables();
        verify(mainActivity).setUpList();
        verify(mainActivity).setContentView(anyInt());
        verify(bottomNavigationView).setSelectedItemId(R.id.person);
        verify(bottomNavigationView).setOnNavigationItemSelectedListener(any());
        verify(adapter).setShoeItemList(anyList());
        verify(recyclerView).setAdapter(adapter);
    }

    @Test
    public void testOnResume() {
        List<ShoeCart> shoeCartList = new ArrayList<>();
        when(viewModel.getAllCartItems()).thenReturn(mockLiveData(shoeCartList));
        mainActivity.onResume();
        verify(viewModel).getAllCartItems();
    }

    @Test
    public void testOnCardClicked() {
        ShoeItem shoeItem = mock(ShoeItem.class);
        Intent intent = mock(Intent.class);

        mainActivity.onCardClicked(shoeItem);

        verify(mainActivity).startActivity(intent);
    }

    @Test
    public void testOnAddToCartBtnClicked() {
        ShoeItem shoeItem = mock(ShoeItem.class);
        ShoeCart shoeCart = mock(ShoeCart.class);
        when(shoeItem.getShoeName()).thenReturn("Test Shoe");
        when(shoeItem.getShoeBrandName()).thenReturn("Test Brand");
        when(shoeItem.getShoePrice()).thenReturn(20.0);
        when(shoeItem.getShoeImage()).thenReturn(123);
        mainActivity.onAddToCartBtnClicked(shoeItem);
        verify(mainActivity).makeSnackBar("Item Added To Cart");
        verify(viewModel).insertCartItem(any());
    }

    private <T> LiveData<T> mockLiveData(T data) {
        LiveData<T> liveData = mock(LiveData.class);
        when(liveData.getValue()).thenReturn(data);
        return liveData;
    }
}
