package com.oo115.myapplication.retrofitAPI;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("register.php")
    Call<RegistrationResponse> performRegistration(@Query("name") String FirstName, @Query("surname") String LastName, @Query("useremail") String Email, @Query("password") String Password);

    @GET("login.php")
    Call<UserDB> performUserLogin(@Query("user_email") String Email, @Query("password") String Password);

    @GET("bodyMeasures.php")
    Call<BodyMeasuresResponse> lastMeasureSearch(@Query("user_id") int user_id, @Query("bodyPart_id") int bodyPart_id);

    @GET("measurementsFor_graph.php")
    Call<BodyMeasuresResponse> measurementsFor_graph(@Query("user_id") int user_id, @Query("bodyPart_id") int bodyPart_id);

    @GET("recentMeasuresAllBP.php")
    Call<BodyMeasuresResponse> recentMeasures(@Query("user_id") int user_id);

    @GET("addMeausre.php")
    Call<BodyMeasuresResponse> addMeasurement(@Query("profile_id") int user_id, @Query("bodyPart") int bodyPart, @Query("measurement") float measurement, @Query("date") java.sql.Date date);

    //performing quote search using user ID
    @GET("quotes.php")
    Call<QuotesDB> performQuoteSearch(@Query("id") int Id);


    @GET("forgotPassword.php")
    Call<ForgotEmailDB> performEmailSearch(@Query("user_email") String Email, @Query("random_password") String password);

    @GET("profile.php")
    Call<Profile_UpdateDB> updateProfile(@Query("user_id") int id, @Query("user_gender") String gender, @Query("user_DOB") java.sql.Date date, @Query("user_height") double height,
                                         @Query("user_goal") String goal);


    @GET("save_marcoCalc.php")
    Call<Profile_UpdateDB> saveMacro_calc(@Query("user_id") int id, @Query("rec_protein") double protein, @Query("rec_carbs") double carbs, @Query("rec_calories") double calories,
                                          @Query("rec_water") double water, @Query("rec_fat") double fat, @Query("goal") String goal, @Query("activity_level") String activity_level);




    @GET("exercise_search.php")
    Call<Exercises_response> findExercises(@Query("muscle_group") int muscleGroup);

    @GET("delete_measure.php")
    Call<Delete_MeasurementDB> delete_measurement(@Query("measure_Id") int measurement_Id);

    @GET("favourites.php")
    Call<FavouritesDB> favourite(@Query("user_id") int user_Id, @Query("exercise_name") String name);

    @GET("unfavourite.php")
    Call<FavouritesDB> unfavourite(@Query("exercise_name") String name, @Query("user_id") int user_id);

    @GET("favourites_search.php")
    Call<Favourites_search_response> search_favourites(@Query("user_id") int user_id);

    @GET("change_password.php")
    Call<Password_changeDB> passwordChange(@Query("user_email") String user_email, @Query("new_password") String user_password);

    @GET("change_email.php")
    Call<Change_EmailDB> emailChange(@Query("user_email") String user_email, @Query("new_email") String new_email);

    @GET("delete_profile.php")
    Call<UserDB> delete_profile(@Query("user_email") String user_email);

    @GET("add_to_plan.php")
    Call<ResponseDB> add_to_plan(@Query("user_id") int user_id, @Query("exercise_id") int exercise_id);

    @GET("remove_from_plan.php")
    Call<ResponseDB> remove_from_plan(@Query("user_id") int user_id, @Query("exercise_id") int exercise_id);

    @GET("search_custom_plan.php")
    Call<Custom_plan_searchResponse> get_custom_plan(@Query("user_id") int user_id);

    @GET("add_weight_measurement.php")
    Call<ResponseDB> add_weight_measurement(@Query("user_id") int user_id, @Query("date") Date date, @Query("measurement") float measurement);

    @GET("add_bodyFat_percentage.php")
    Call<ResponseDB> add_bodyFat_measurement(@Query("user_id") int user_id, @Query("date") Date date, @Query("measurement") float measurement);

    @GET("add_Muscle_Percentage.php")
    Call<ResponseDB> add_musclePercentage_measurement(@Query("user_id") int user_id, @Query("date") Date date, @Query("measurement") float measurement);

    @GET("add_water_percentage.php")
    Call<ResponseDB> add_waterPercentage_measurement(@Query("user_id") int user_id, @Query("date") Date date, @Query("measurement") float measurement);

    @GET("search_weight_meausrement.php")
    Call<WeightMeasurement_Response> search_weight_measurements(@Query("user_id") int user_id, @Query("measurement_type") String measurement_type);

    @GET("delete_weight.php")
    Call<ResponseDB> delete_weight(@Query("measure_Id") int measurement_id, @Query("measurement_type") String measurement_type);


    @GET("joint_weight_search.php")
    Call<Recent_weightSearch> recent_weight_measurements(@Query("measure_Id") int measurement_id);


    @GET("removeWorkoutHistory.php")
    Call<ResponseDB> removeFromHistory(@Query("user_id") int user_id, @Query("history_id") int history_id);



    @GET("weightMeasurements_forGraph.php")
    Call<WeightMeasurement_Response> search_weight_measurements_forGraph(@Query("user_id") int user_id, @Query("measurement_type") String measurement_type);

    @GET("addExerciseHistory.php")
    Call<ResponseDB> add_exercise_history(@Query("date") Date date, @Query("string_date") String date_string, @Query("reps") int reps, @Query("sets") int sets, @Query("weight") double weight, @Query("user_id") int user_id, @Query("exercise_name") String exercise_name);

    @GET("readEx_log.php")
    Call<Exercise_historyResponse> get_workout_num(@Query("user_id") int user_id, @Query("ex_name") String ex_name);


}