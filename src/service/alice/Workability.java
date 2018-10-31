package service.alice;

import com.google.gson.Gson;

interface Workability{
    String ConvertToGson();
    void ConvertFromGson(Gson gsoni);
}
