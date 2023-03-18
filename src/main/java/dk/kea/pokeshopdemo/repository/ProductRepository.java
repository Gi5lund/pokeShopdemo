package dk.kea.pokeshopdemo.repository;

import dk.kea.pokeshopdemo.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository
	{
		// database properties
		private final static	String db_URL="jdbc:mysql://localhost:3306/pokedex";
		private final String uid="root";
		private final String pwd="Pegasus2606";

		public List<Pokemon> getAll(){
			List<Pokemon> productList=new ArrayList<>();
			try {
				Connection connection= DriverManager.getConnection(db_URL,uid,pwd);
				Statement statement=connection.createStatement();
				final String SQL_QUERY="SELECT * FROM pokedex.pokemon";
				ResultSet resultSet=statement.executeQuery(SQL_QUERY);
				while (resultSet.next()){
					int id=resultSet.getInt(1);
					String name=resultSet.getString(2);
					int speed=resultSet.getInt(3);
					int special_defence=resultSet.getInt(4);
					int special_attack=resultSet.getInt(5);
					int defence=resultSet.getInt(6);
					int attack=resultSet.getInt(7);
					int hp=resultSet.getInt(8);
					String primary_type=resultSet.getString(9);
					String secondary_type=resultSet.getString(10);
					Pokemon pokemon=new Pokemon(id,name,speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type);
					productList.add(pokemon);
					System.out.println(pokemon);
				}
			}catch (SQLException e){
				System.out.println("could not query database");
				e.printStackTrace();
			}
			return productList;
		}


	}
