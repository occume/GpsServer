package mybatis.mapper;

import java.util.List;

import mybatis.domain.TrackBean;
import mybatis.domain.User;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

  void addUser(User user);
  
  void batchAddUser(@Param("list")List<User> users); 
  
  void createTable(String tableName);
  
  String getDeptById(int id);
  
  void addTrack(TrackBean track);
  
  List<String> getAllTrackTables();
  
}
