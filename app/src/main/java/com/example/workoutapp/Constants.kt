package com.example.workoutapp

object Constants{
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()

        val jumpingjacks = ExerciseModel(1,"jumping jacks",R.drawable.ic_jumping_jacks,false,false)
        exerciseList.add(jumpingjacks)

        val wallSit = ExerciseModel(2,"Wall Sit",R.drawable.ic_wall_sit,false,false)
        exerciseList.add(wallSit)

        val pushUps = ExerciseModel(3,"Push Ups",R.drawable.ic_push_up,false,false)
        exerciseList.add(pushUps)

        val abdominalCrunch = ExerciseModel(4,"Abdominal Crunch",R.drawable.ic_abdominal_crunch,false,false)
        exerciseList.add(abdominalCrunch)

        val high_knees_running = ExerciseModel(5,"High Knees Running",R.drawable.ic_high_knees_running_in_place,false,false)
        exerciseList.add(high_knees_running)

        val lunges=ExerciseModel(6,"Lunges",R.drawable.ic_lunge,false,false)
        exerciseList.add(lunges)

        val plank=ExerciseModel(7,"Plank",R.drawable.ic_plank,false,false)
        exerciseList.add(plank)

        val push_up_and_rotation=ExerciseModel(8,"Push up and Rotation",R.drawable.ic_push_up_and_rotation,false,false)
        exerciseList.add(push_up_and_rotation)

        val step_up_onto_chair = ExerciseModel(9,"Step Up Onto Chair",R.drawable.ic_step_up_onto_chair,false,false)
        exerciseList.add(step_up_onto_chair)

        val triceps_dips = ExerciseModel(10,"Triceps Dips",R.drawable.ic_triceps_dip_on_chair,false,false)
        exerciseList.add(triceps_dips)

        val side_plank = ExerciseModel(11,"Side Plank",R.drawable.ic_side_plank,false,false)
        exerciseList.add(side_plank)

        val squat = ExerciseModel(12,"Squats",R.drawable.ic_squat,false,false)
        exerciseList.add(squat)



        return exerciseList
    }
}