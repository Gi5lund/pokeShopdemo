package dk.kea.pokeshopdemo.repository;


import dk.kea.pokeshopdemo.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository
	{
		// database properties
	//	private final static	String db_URL="jdbc:mysql://localhost:3306/pokedex";
	//	private final String uid="root";
		//private final String pwd="Pegasus2606";
		@Value("${spring.datasource.url}")
		private String db_URL;
		@Value("${spring.datasource.username}")
		private String uid;
		@Value("@{spring.datasource.password}")
		private String pwd;

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

				}
			}catch (SQLException e){
				System.out.println("could not query database");
				e.printStackTrace();
			}
			return productList;
		}


		public void addPokemon(Pokemon newPokemon)
			{
				try {
					Connection connection=DriverManager.getConnection(db_URL,uid,pwd);
					final String addToPokedex="INSERT INTO pokedex.pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type) VALUES(?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement preparedStatement=connection.prepareStatement(addToPokedex);

					preparedStatement.setInt(1,newPokemon.getId());
					preparedStatement.setString(2,newPokemon.getName());
					preparedStatement.setInt(3,newPokemon.getSpeed());
					preparedStatement.setInt(4,newPokemon.getSpecial_defence());
					preparedStatement.setInt(5,newPokemon.getSpecial_attack());
					preparedStatement.setInt(6,newPokemon.getDefence());
					preparedStatement.setInt(7,newPokemon.getAttack());
					preparedStatement.setInt(8,newPokemon.getHp());
					preparedStatement.setString(9,newPokemon.getPrimary_type());
					preparedStatement.setString(10,newPokemon.getSecondary_type());

					preparedStatement.executeUpdate();


				}catch (SQLException e){
					System.out.println("could not create new product");
					e.printStackTrace();
				}
			}
	}
