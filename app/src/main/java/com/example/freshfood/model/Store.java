package com.example.freshfood.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


@Entity(tableName="store")
public class Store implements Parcelable {

    @ColumnInfo(name="store_id")
    @PrimaryKey
    private int storeId;

    private List<Food> foodList;

    @Expose
    @SerializedName("store_name")
    @ColumnInfo(name="store_name")
    private String storeName;

    @Expose
    @SerializedName("store_owner")
    @ColumnInfo(name="store_owner")
    private String storeOwner;

    @Expose
    @SerializedName("store_location")
    @ColumnInfo(name="store_location")
    private String storeLocation;

    @Expose
    @SerializedName("image_store")
    @ColumnInfo(name="image_store")
    private int imgStore;

    @Expose
    @SerializedName("owner_image")
    @ColumnInfo(name="owner_image")
    private int imgOwner;




    public Store(int storeId, String storeName, String storeOwner, String storeLocation) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeOwner = storeOwner;
        this.storeLocation = storeLocation;
        this.foodList = new ArrayList<>();
    }
    public Store(int storeId, String storeName, String storeOwner, String storeLocation, int imgStore, int imgOwner) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeOwner = storeOwner;
        this.storeLocation = storeLocation;
        this.imgStore = imgStore;
        this.imgOwner = imgOwner;
        this.foodList = new ArrayList<>();
    }

    protected Store(Parcel parcel) {
        this.storeId = parcel.readInt();
        this.storeName = parcel.readString();
        this.storeOwner = parcel.readString();
        this.storeLocation = parcel.readString();
        this.imgStore = parcel.readInt();
        this.imgOwner = parcel.readInt();
    }
    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreOwner(String storeOwner) {
        this.storeOwner = storeOwner;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void setImgStore(int imgStore) {
        this.imgStore = imgStore;
    }

    public void setImgOwner(int imgOwner) {
        this.imgOwner = imgOwner;
    }



    public int getStoreId() {
        return storeId;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreOwner() {
        return storeOwner;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public int getImgStore() {
        return imgStore;
    }

    public int getImgOwner() {
        return imgOwner;
    }






    public boolean isExisted(Food item) {
        for (Food food : foodList) {
            if(food.getName().equalsIgnoreCase(item.getName())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean isDifferentStore(Food item) {
        for(Food food: foodList) {
            if(!food.getStore().getStoreName().equalsIgnoreCase(item.getStore().getStoreName())) {
                return true;
            }
        }
        return false;
    }

    public void insertNewFood(Food food) {
        if(isExisted(food) || isDifferentStore(food)) {
            return;
        }
        foodList.add(food);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.storeId);
            dest.writeString(this.storeName);
            dest.writeString(this.storeOwner);
            dest.writeString(this.storeLocation);
            dest.writeInt(this.imgOwner);
            dest.writeInt(this.imgStore);

    }

    public static final Parcelable.Creator<Store> CREATOR = new Parcelable.Creator<Store>() {


        @Override
        public Store createFromParcel(Parcel source) {
                return new Store(source);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
}
