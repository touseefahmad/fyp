package com.tikrosoft.resturantapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tikrosoft.resturantapp.pojo.Cart;
import com.tikrosoft.resturantapp.pojo.item.Item;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import static com.tikrosoft.resturantapp.ActivityHome.cartList;

public class ActivityAddToCart extends AppCompatActivity implements View.OnClickListener {
    TextView tvMenuNameCart;
    TextView tvMenuDescCart;
    TextView tvMenuPriceCart;
    NumberPicker qty_picker;
    Button btnAddCartDone;
    Button btnShowCart;
    Cart cartItem;
    Item item;
    String unitPrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        init();
    }
    public void init(){
        tvMenuNameCart = findViewById(R.id.tvMenuNameCart);
        tvMenuDescCart = findViewById(R.id.tvMenuDescCart);
        tvMenuPriceCart = findViewById(R.id.tvMenuPriceCart);
        qty_picker = findViewById(R.id.qty_picker);
        btnAddCartDone = findViewById(R.id.btnAddCartDone);
        btnShowCart = findViewById(R.id.btnShowCart);
        qty_picker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                // Toast.makeText(getApplicationContext(),"value changed"+value,Toast.LENGTH_SHORT).show();
                Log.e("Value Changed",""+value);
                cartItem.setQuantiry(Integer.toString(value));

                String intValue= unitPrice.replaceAll("[A-z]+", "");
                int priceValue = Integer.parseInt(intValue)*value;
                tvMenuPriceCart.setText(Integer.toString(priceValue));
                cartItem.setItemPrice(Integer.toString(priceValue));


            }
        });

        Item item = new Item();
        item=(Item) getIntent().getSerializableExtra("Item");
        cartItem = new Cart();
        cartItem.setItemName(item.getMenuItemName());
        cartItem.setItemDescription(item.getMenuItemDescription());
        cartItem.setItemPrice(item.getMenuItemPrice());
        unitPrice = item.getMenuItemPrice();
        cartItem.setUnitPrice(unitPrice);
        cartItem.setItemId(item.getMenuId());

        btnAddCartDone.setOnClickListener(this);
        btnShowCart.setOnClickListener(this);

        tvMenuNameCart.setText(cartItem.getItemName());
        tvMenuDescCart.setText(cartItem.getItemDescription());
        tvMenuPriceCart.setText(cartItem.getItemPrice());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnAddCartDone){
            cartList.add(cartItem);
            this.finish();
        }
        if(view.getId() ==R.id.btnShowCart){
            Intent showCart = new Intent(this,ActivityShowCart.class);
            startActivity(showCart);
        }

    }
}
