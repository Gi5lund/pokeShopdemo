package dk.kea.pokeshopdemo.repository;


import dk.kea.pokeshopdemo.model.Pokemon;
import dk.kea.pokeshopdemo.utility.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository
	{
		// database properties
//		private final static	String db_URL="jdbc:mysql://localhost:3306/pokedex";
//		private final String uid="root";
//		private final String pwd="Pegasus2606";


		@Value("${spring.datasource.url}")
		private String db_URL;
		@Value("${spring.datasource.username}")
		private String uid;
		@Value("${spring.datasource.password}")
		private String pwd;

		public ProductRepository()
			{
			}

		public List<Pokemon> getAll()
			{
				List<Pokemon> productList = new ArrayList<>();
				try {
					Connection connection = ConnectionManager.getConnection(db_URL, uid, pwd);
					Statement statement = connection.createStatement();
					final String SQL_QUERY = "SELECT * FROM pokedex.pokemon";
					ResultSet resultSet = statement.executeQuery(SQL_QUERY);
					while (resultSet.next()) {
						int id = resultSet.getInt(1);
						String name = resultSet.getString(2);
						int speed = resultSet.getInt(3);
						int special_defence = resultSet.getInt(4);
						int special_attack = resultSet.getInt(5);
						int defence = resultSet.getInt(6);
						int attack = resultSet.getInt(7);
						int hp = resultSet.getInt(8);
						String primary_type = resultSet.getString(9);
						String secondary_type = resultSet.getString(10);
						Pokemon pokemon = new Pokemon(id, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type);
						productList.add(pokemon);

					}
				}
				catch (SQLException e) {
					System.out.println("could not query database");
					e.printStackTrace();
				}
				return productList;
			}


		public void addPokemon(Pokemon newPokemon)
			{
				try {
					Connection connection = ConnectionManager.getConnection(db_URL, uid, pwd);
					final String addToPokedex = "INSERT INTO pokedex.pokemon(pokedex_number, name, speed, special_defence, special_attack, defence, attack, hp, primary_type, secondary_type) VALUES(?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement preparedStatement = connection.prepareStatement(addToPokedex);

					preparedStatement.setInt(1, newPokemon.getId());
					preparedStatement.setString(2, newPokemon.getName());
					preparedStatement.setInt(3, newPokemon.getSpeed());
					preparedStatement.setInt(4, newPokemon.getSpecial_defence());
					preparedStatement.setInt(5, newPokemon.getSpecial_attack());
					preparedStatement.setInt(6, newPokemon.getDefence());
					preparedStatement.setInt(7, newPokemon.getAttack());
					preparedStatement.setInt(8, newPokemon.getHp());
					preparedStatement.setString(9, newPokemon.getPrimary_type());
					preparedStatement.setString(10, newPokemon.getSecondary_type());

					preparedStatement.executeUpdate();


				}
				catch (SQLException e) {
					System.out.println("could not create new product");
					e.printStackTrace();
				}
			}

		public void updatePokemon(Pokemon pokemon)
			{
				// get connection
				try {


					Connection connection = ConnectionManager.getConnection(db_URL, uid, pwd);
					// SQL Statement
					final String UPDATE_QUERY = "UPDATE pokemon SET name=?, speed=?, special_defence=?, special_attack=?, defence=?, attack=?, hp=?, primary_type=?, secondary_type=? WHERE pokedex_number=?";

					//Prepared Statement
					PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

					// set parameters
					int pokedex_number = pokemon.getId();
					String name = pokemon.getName();
					int speed = pokemon.getSpeed();
					int special_defence = pokemon.getSpecial_defence();
					int special_attack = pokemon.getSpecial_attack();
					int defence = pokemon.getDefence();
					int attack = pokemon.getAttack();
					int hp = pokemon.getHp();
					String primary_type = pokemon.getPrimary_type();
					String secondary_type = pokemon.getSecondary_type();
					preparedStatement.setInt(10, pokedex_number);
					preparedStatement.setString(1, name);
					preparedStatement.setInt(2, speed);
					preparedStatement.setInt(3, special_defence);
					preparedStatement.setInt(4, special_attack);
					preparedStatement.setInt(5, defence);
					preparedStatement.setInt(6, attack);
					preparedStatement.setInt(7, hp);
					preparedStatement.setString(8, primary_type);
					preparedStatement.setString(9, secondary_type);

					// execute prepared statement
					preparedStatement.executeUpdate();
				}
				catch (SQLException e) {
					System.out.println("could not update pokemon");
					e.printStackTrace();
				}
			}

			public Pokemon findPokemonByID(int pokeID){
			//SQL-Statement
				final  String FIND_QUERY="SELECT * FROM pokemon WHERE pokedex_number=?";
				Pokemon pokemon=new Pokemon();
				pokemon.setId(pokeID);
				try {
					//get connection
					Connection connection=ConnectionManager.getConnection(db_URL,uid,pwd);
					//prepared statement
					PreparedStatement preparedStatement=connection.prepareStatement(FIND_QUERY);
					// set parameters
					preparedStatement.setInt(1,pokeID);
					//execute statement
					ResultSet resultSet=preparedStatement.executeQuery();
					String name=resultSet.getString(2);
					int speed=resultSet.getInt(3);
					int special_defence=resultSet.getInt(4);
					int special_attack=resultSet.getInt(5);
					int defence=resultSet.getInt(6);
					int attack=resultSet.getInt(7);
					int hp=resultSet.getInt(8);
					String primary_type=resultSet.getString(9);
					String secondary_type=resultSet.getString(10);
					pokemon.setSpeed(speed);
					pokemon.setHp(hp);
					pokemon.setAttack(attack);
					pokemon.setDefence(defence);
					pokemon.setName(name);
					pokemon.setSecondary_type(secondary_type);
					pokemon.setPrimary_type(primary_type);
					pokemon.setSpecial_defence(special_defence);
					pokemon.setSpecial_attack(special_attack);
					//
				}catch(SQLException e){
					System.out.println("could not find Pokemon!");
					e.printStackTrace();
				}
				return pokemon;
			}

			public void deleteByID(int pokeID){
			final String DELETE_QUERY="DELETE FROM pokemon WHERE pokedex_number=?";
			try {
				Connection connection=ConnectionManager.getConnection(db_URL,uid,pwd);
				PreparedStatement preparedStatement=connection.prepareStatement(DELETE_QUERY);
				preparedStatement.setInt(1,pokeID);
				preparedStatement.executeUpdate();
			}catch(SQLException e){
				System.out.println("could not delete pokemon!");
				e.printStackTrace();
			}
			}

	}
