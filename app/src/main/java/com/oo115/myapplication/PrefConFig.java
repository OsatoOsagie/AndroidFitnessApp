package com.oo115.myapplication;


/**
 * This class is to save the user preferences
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConFig {

    private SharedPreferences sharedPreferences;
    private Context context;


    public PrefConFig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file),Context.MODE_PRIVATE);


    }

    /**
     * this method writes the user login status to the shared preferences
     */

    public void writeLoginStatus(boolean status){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),status);
        editor.apply();


    }

    /**
     *this method reads the user login status from the shared preferences
     */

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status),false);
    }


    /**
     *  //this method writes the name of the user to the shared preferences
     */


    public void writeName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),name);
        editor.apply();
    }


    /**
     *  //this method reads the name of the user to the shared preferences
     */
    public String readName(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_name), "user default");
    }


    /**
     *  //this method is for displaying messages to the users
     */
    public void distplayToast(String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }


    /**
     *  //this method writes quotes from the database for the landing page
     */
    public void writeQuote(String quote){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_quote),quote);
        editor.apply();

    }


    /**
     *  //this method Reads quotes from the database for the landing page
     */
    public String readQuote(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_quote), "no quote found");
    }


    /**
     *  //this method  writes the user email
     */
    public void writeEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_email), email);
        editor.apply();
    }


    /**
     *  //this method Reads the user email
     */

    public String readEmail() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_email), "no email found");
    }

    /**
     *  //this method writes the user ID
     */

    public void writeUser_Id(String id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_id), id);
        editor.apply();
    }

    /**
     *  This method reads the user ID
     */

    public String readId() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_id), "no ID found");
    }

    /**
     *  this method writes the users sex
     */

    public void writeUser_sex(String sex) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_sex), sex);
        editor.apply();
    }
    /**
     *  This method reads the user sex
     */
    public String readSex() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_sex), "no sex found");
    }


    /**
     *  this method writes the users dob
     */

    public void writeUser_dob(String dob) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_dob), dob);
        editor.apply();
    }
    /**
     *  This method reads the user dob
     */
    public String readDob() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_dob), "no dob found");
    }

    /**
     *  this method writes the users height
     */
    void writeUser_height(String height) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_height), height);
        editor.apply();
    }
    /**
     *  This method reads the user height
     */
    public String readHeight() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_height), "no height found");
    }


    /**
     *  this method writes the users height
     */
    void writeUser_goal(String goal) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_goal), goal);
        editor.apply();
    }

    /**
     *  This method reads the user height
     */
    public String readGoal() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_goal), "no goal found");
    }

    /**
     *  this method writes the users password
     */
    public void writeUser_password(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_password), password);
        editor.apply();
    }

    /**
     *  This method reads the user height
     */
    public String readPassword() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_password), "no password found");
    }


    /**
     * this method writes the users weight
     */
    public void writeUser_weight(String weight) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_weight), weight);
        editor.apply();
    }

    /**
     * This method reads the user height
     */
    public String readWeight() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_weight), "no weight found");
    }

    /**
     * this method writes the users recommended protein
     */
    public void writeUser_protein(String protein) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_rec_protein), protein);
        editor.apply();
    }

    /**
     * This method reads the user recommened protein
     */
    public String readProtein() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_rec_protein), "-");
    }

    /**
     * this method writes the users recommended carbs
     */
    public void writeUser_carbs(String carbs) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_rec_carbs), carbs);
        editor.apply();
    }

    /**
     * This method reads the user recommened crabs
     */
    public String readCarbs() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_rec_carbs), "-");
    }

    /**
     * this method writes the users recommended fats
     */
    public void writeUser_fat(String fat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_rec_fat), fat);
        editor.apply();
    }

    /**
     * This method reads the user recommened fats
     */
    public String readFats() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_rec_fat), "-");
    }


    /**
     * this method writes the users recommended water intake
     */
    public void writeUser_water(String water) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_rec_water), water);
        editor.apply();
    }

    /**
     * This method reads the user recommened water
     */
    public String readWater() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_rec_water), "-");
    }


    /**
     * this method writes the users recommended water intake
     */
    public void writeUser_calories(String calories) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_rec_calories), calories);
        editor.apply();
    }

    /**
     * This method reads the user recommened water
     */
    public String readCalories() {
        return sharedPreferences.getString(context.getString(R.string.pref_user_rec_calories), "-");
    }

    /**
     * This method is used to sign a user out of the application
     */
    public void log_out() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        writeLoginStatus(false);
        editor.apply();


    }

    /**
     * This method is used to set the users weekly taget
     */
    public void writeUser_weeklyTarget(String target) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.target_goal), target);
        editor.apply();
    }

    /**
     * This method reads the users weekly target
     */
    public String readWeeklyTarget() {
        return sharedPreferences.getString(context.getString(R.string.target_goal), "-");
    }

    /**
     * This method is used to set the users weekly activityLevel
     */
    public void writeUser_weeklyActivityLevel(String activity_level) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.activity_levelPerWeek), activity_level);
        editor.apply();
    }

    /**
     * This method reads the users weekly target
     */
    public String readActivityLevel() {
        return sharedPreferences.getString(context.getString(R.string.activity_levelPerWeek), "-");
    }



}
