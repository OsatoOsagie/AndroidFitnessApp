package com.oo115.myapplication;

import com.oo115.myapplication.retrofitAPI.ApiInterface;

public class BodyPart {
    public static final int SHOWALLEXERCIES = 0;
    public static final int LEFTARM = 1;
    public static final int RIGHTARM = 2;
    public static final int PECTORAUX = 3;
    public static final int WAIST = 4;
    public static final int BEHIND = 5;
    public static final int LEFTTHIGH = 6;
    public static final int RIGHTTHIGH = 7;
    public static final int LEFTCALVES = 8;
    public static final int RIGHTCALVES = 9;

    public static final int ABDOMINAUX = 10;
    public static final int ADDUCTEURS = 11;
    public static final int BICEPS = 12;
    public static final int TRICEPS = 13;
    public static final int DELTOIDS = 14;
    public static final int MOLLETS = 15;
    public static final int DORSEAUX = 16;
    public static final int QUADRICEPS = 17;
    public static final int ISCHIOJAMBIERS = 18;
    public static final int NECK = 19;
    public static final int WEIGHT = 20;
    public static final int FAT = 21;
    public static final int BONES = 22;
    public static final int WATER = 23;
    public static final int MUSCLES = 24;

    private int id;    // Notez que l'identifiant est un long
    private int mLastMeasure;
    String[] armMeasurements = new String[5];
    public static ApiInterface apiInterface;

    public BodyPart(int id) {
        super();
        this.id = id;
        this.mLastMeasure = 0;
    }

    public BodyPart(int id, int lastMeasure) {
        super();
        this.id = id;
        this.mLastMeasure = lastMeasure;
    }

    private static int getBodyResourceID(int pBodyID) {
        switch (pBodyID) {
            case ABDOMINAUX:
                return R.string.abdominaux;
            case ADDUCTEURS:
                return R.string.adducteurs;
            case BICEPS:
                return R.string.biceps;
            case TRICEPS:
                return R.string.triceps;
            case DELTOIDS:
                return R.string.deltoids;
            case MOLLETS:
                return R.string.mollets;
            case PECTORAUX:
                return R.string.pectoraux;
            case DORSEAUX:
                return R.string.dorseaux;
            case QUADRICEPS:
                return R.string.quadriceps;
            case ISCHIOJAMBIERS:
                return R.string.ischio_jambiers;
            case LEFTARM:
                return R.string.left_arm;
            case RIGHTARM:
                return R.string.right_arm;
            case LEFTTHIGH:
                return R.string.left_thigh;
            case RIGHTTHIGH:
                return R.string.right_thigh;
            case LEFTCALVES:
                return R.string.left_calves;
            case RIGHTCALVES:
                return R.string.right_calves;
            case WAIST:
                return R.string.waist;
            case NECK:
                return R.string.neck;
            case BEHIND:
                return R.string.behind;
            case WEIGHT:
                return R.string.weightLabel;
            case FAT:
                return R.string.fatLabel;
            case BONES:
                return R.string.bonesLabel;
            case WATER:
                return R.string.waterLabel;
            case MUSCLES:
                return R.string.musclesLabel;
        }

        return 0;
    }

    private static int getBodyLogoID(int pBodyID) {
        switch (pBodyID) {
            case ABDOMINAUX:
                return R.drawable.ic_chest;
            case ADDUCTEURS:
                return R.drawable.ic_leg;
            case BICEPS:
                return R.drawable.ic_arm;
            case TRICEPS:
                return R.drawable.ic_arm;
            case DELTOIDS:
                return R.drawable.ic_chest;
            case MOLLETS:
                return R.drawable.ic_leg;
            case PECTORAUX:
                return R.drawable.ic_chest_measure;
            case DORSEAUX:
                return R.drawable.ic_chest;
            case QUADRICEPS:
                return R.drawable.ic_leg;
            case ISCHIOJAMBIERS:
                return R.drawable.ic_leg;
            case LEFTARM:
                return R.drawable.ic_arm_measure;
            case RIGHTARM:
                return R.drawable.ic_arm_measure;
            case LEFTTHIGH:
                return R.drawable.ic_tight_measure;
            case RIGHTTHIGH:
                return R.drawable.ic_tight_measure;
            case LEFTCALVES:
                return R.drawable.ic_calve_measure;
            case RIGHTCALVES:
                return R.drawable.ic_calve_measure;
            case WAIST:
                return R.drawable.ic_waist_measure;
            case NECK:
                return R.drawable.ic_neck;
            case BEHIND:
                return R.drawable.ic_buttock_measure;
        }

        return 0;
    }

    public long getId() {
        return id;
    }

    /**
     * @return Resource ID of the name of the body part
     */
    public int getResourceNameID() {
        return getBodyResourceID(id);
    }

    /**
     * @return Resource ID of the logo
     */
    public int getResourceLogoID() {
        return getBodyLogoID(id);
    }

    public int getLastMeasure() {
        return mLastMeasure;
    }

    public void setLastMeasure(int lastmeasure) {
        this.mLastMeasure = lastmeasure;
    }


}
