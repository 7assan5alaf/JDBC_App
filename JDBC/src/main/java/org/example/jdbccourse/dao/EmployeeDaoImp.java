package org.example.jdbccourse.dao;
import org.example.jdbccourse.dao.utils.Utils;
import org.example.jdbccourse.model.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
public class EmployeeDaoImp implements EmployeeDao{
    Connection connection;

    {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @Override
    public List<Employee> findAll() {
       if (connection==null)return null;
        List<Employee>employees=new LinkedList<>();
        String query="SELECT * FROM employee;";

       try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
           ResultSet resultSet=preparedStatement.executeQuery();
           while (resultSet.next()) {
              Employee employee=Employee.builder()
                      .id(resultSet.getInt("id"))
                      .gender(resultSet.getBoolean("gender"))
                      .name(resultSet.getString("name"))
                      .birth_date(resultSet.getDate("birth_date"))
                      .salary(resultSet.getDouble("salary"))
                      .build();

               employees.add(employee);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }finally {
           try {
               connection.close();
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
       }
        return employees;
    }

    @Override
    public Employee findById(int id) {
        if (connection==null)return null;
        String query="SELECT * FROM employee WHERE id=?;";
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
              return Employee.builder()
                      .id(resultSet.getInt("id"))
                      .gender(resultSet.getBoolean("gender"))
                      .name(resultSet.getString("name"))
                      .birth_date(resultSet.getDate("birth_date"))
                      .salary(resultSet.getDouble("salary"))
                      .build();
            }

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        if (connection==null)return;
        String query="DELETE FROM employee WHERE id=?;";
        try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Delete employee");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }


    }

    @Override
    public void save(Employee e) {

        if(connection==null){
          return;
        }
        if (e.getId()>0){
            //update
            String query="UPDATE employee SET  name=?,gender=?,birth_date=?,salary=? WHERE id=?";
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
                preparedStatement.setString(1,e.getName());
                preparedStatement.setBoolean(2,e.getGender());
                preparedStatement.setDate(3, Utils.getSqlDate(e.getBirth_date()));
                preparedStatement.setDouble(4,e.getSalary());
                preparedStatement.setInt(5,e.getId());
                preparedStatement.executeUpdate();

            }catch (SQLException se){
                se.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }else {
            //insert
            String query="INSERT INTO employee(name,gender,birth_date,salary) VALUE(?,?,?,?)";
            try(PreparedStatement preparedStatement=connection.prepareStatement(query)) {
                preparedStatement.setString(1,e.getName());
                preparedStatement.setBoolean(2,e.getGender());
                preparedStatement.setDate(3, Utils.getSqlDate(e.getBirth_date()));
                preparedStatement.setDouble(4,e.getSalary());
                preparedStatement.executeUpdate();

            }catch (SQLException se){
                se.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
            }

        }
}
