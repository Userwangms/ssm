package com.wxf.service;

import com.wxf.dao.SysUserMapper;
import com.wxf.pojo.SysRoles;
import com.wxf.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    //User接口 此处注入UserMapper时需要在UserMapper接口上添加<span style="font-family: Arial, Helvetica, sans-serif;">@Component进行依赖注入</span>    
    @Autowired
    private SysUserMapper userMapper;

    public SysUser findUser() throws Exception {
        //调用mapper类中的selectByExample方法，如果传入类型为null，则表示无条件查找    
        SysUser users = userMapper.selectByPrimaryKey(1);
        return users;  
    }

    @Override
    public SysUser getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public SysRoles getRoles(SysUser sysUser) {
        return userMapper.getRoles(sysUser);
    }


/**
 * redis 取值
 */
    /*@Transactional
    public List<Area> getAreaList(){// 定义redis的key
        String key = AREALISTKEY;
        // 定义接收对象
        List<Area> areaList = null;
        // 定义jackson数据转换操作类
        ObjectMapper mapper = new ObjectMapper();
        // 判断key是否存在
        if (!jedisKeys.exists(key)) {
            // 若不存在，则从数据库里面取出相应数据
            areaList = areaDao.queryArea();
            // 将相关的实体类集合转换成string,存入redis里面对应的key中
            String jsonString;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            // 若存在，则直接从redis里面取出相应数据
            String jsonString = jedisStrings.get(key);
            // 指定要将string转换成的集合类型
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                // 将相关key对应的value里的的string转换成对象的实体类集合
                areaList = mapper.readValue(jsonString, javaType);
            } catch (JsonParseException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            } catch (JsonMappingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }*/
} 