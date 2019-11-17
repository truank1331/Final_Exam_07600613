package com.example.finalexam07600613.Database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class UserDatabaseRepository {
    private Context context;
    private Context mContent;
    public UserDatabaseRepository(Context context){
        this.context = context;
        this.mContent = context;
    }
    public interface GetCallback{
        void onSuccess(List<UserDatabaseEntity> entityList);
    }
    public void getUser(GetCallback callback){
        GetTask getTask = new GetTask(context,callback);
        getTask.execute();
    }
    private static class GetTask extends AsyncTask<Void,Void,List<UserDatabaseEntity>> {
        private Context context;
        private GetCallback callback;

        public GetTask(Context context,GetCallback callback){
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(List<UserDatabaseEntity> entityList) {
            super.onPostExecute(entityList);
            callback.onSuccess(entityList);
        }

        @Override
        protected List<UserDatabaseEntity> doInBackground(Void... voids) {
            UserDatabase db = UserDatabase.getInstance(context);
            List<UserDatabaseEntity> itemList  = db.UserDatabaseDao().getAll();
            return itemList;
        }
    }

    public interface InsertCallback{
        void onSuccess();
    }
    public void insertAppBoard(UserDatabaseEntity entity,InsertCallback callback){
        InsertTask insertTask = new InsertTask(mContent,callback);
        insertTask.execute(entity);
    }
    private static class InsertTask extends AsyncTask<UserDatabaseEntity,Void,Void>{
        private Context context;
        private InsertCallback callback;
        public InsertTask(Context context,InsertCallback callback){
            this.context = context;
            this.callback = callback;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            callback.onSuccess();
        }

        @Override
        protected Void doInBackground(UserDatabaseEntity... appDatabaseEntities) {
            UserDatabase db = UserDatabase.getInstance(context);
            for (UserDatabaseEntity item : appDatabaseEntities){
                db.UserDatabaseDao().insert(item);
            }
            return null;
        }
    }

}
