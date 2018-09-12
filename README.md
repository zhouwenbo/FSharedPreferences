# An android SharedPreference library.

This is an android SharedPreference library. Is process safe and user dynamic proxy, you can use annotation very easy.
- Support muti-process.
- Use annotation very easy.

# Notice that.

All values are save in main process, if you are not in main process, the operation will switch into main process by ContentProvider.

### Maven

	<dependency>
      <groupId>com.fheebiy.fsp</groupId>
      <artifactId>fsp</artifactId>
      <version>1.0.2</version>
      <type>pom</type>
	</dependency>

### Gradle
    compile 'com.fheebiy.fsp:fsp:1.0.2'


# Usage

- #### Init
        
      FspManager.getInstance().init(applicaion, "default_sp_name");
      
- ### Define interface


      public interface ISpConfig {

          @KEY("key_user_id")
          @DEFAULT_STRING("bob zhou")
          Call<String> userId();

          @KEY("key_index")
          @DEFAULT_INT(-1)
          @SP_FILE("test_sp_file")
          Call<Integer> index();

          @KEY("key_success")
          Call<Boolean> isSuccess();

          @KEY("key_price")
          Call<Float> price();

          @KEY("key_time")
          Call<Long> time();
      }
      
- ### Get Value

         String userId = FspManager.getAppPref(ISpConfig.class).userId().get();
         
- ### Put Value

        FspManager.getAppPref(ISpConfig.class).userId().put("1637");
        
- ### Remove Value
        
        FspManager.getAppPref(ISpConfig.class).userId().remove();
        
