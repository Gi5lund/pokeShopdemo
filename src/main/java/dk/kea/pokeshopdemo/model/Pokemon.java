package dk.kea.pokeshopdemo.model;

public class Pokemon
	{

		private int id;
		private String name;

		public int getSpeed()
			{
				return speed;
			}

		public void setSpeed(int speed)
			{
				this.speed = speed;
			}

		private int speed;
		private int special_defence;
		private int special_attack;
		private int defence;
		private int attack;
		private int hp;
		private String primary_type;
		private String secondary_type;

		public Pokemon()
			{
			}

		public Pokemon(int id, String name, int speed, int special_defence, int special_attack, int defence, int attack, int hp, String primary_type, String secondary_type)
			{
				this.id = id;
				this.name = name;
				this.speed=speed;
				this.special_defence = special_defence;
				this.special_attack = special_attack;
				this.defence = defence;
				this.attack = attack;
				this.hp = hp;
				this.primary_type = primary_type;
				this.secondary_type = secondary_type;
			}

		@Override
		public String toString()
			{
				return "Pokemon{" +
						"id=" + id +
						", name='" + name + '\'' +
						", speed="+speed+
						", special defence=" + special_defence +
						", special attack=" + special_attack +
						", defence=" + defence +
						", attack=" + attack +
						", hp=" + hp +
						", primary type='" + primary_type + '\'' +
						", secondary type='" + secondary_type + '\'' +
						'}';
			}

		public int getId()
			{
				return id;
			}

		public void setId(int id)
			{
				this.id = id;
			}

		public String getName()
			{
				return name;
			}

		public void setName(String name)
			{
				this.name = name;
			}

		public int getSpecial_defence()
			{
				return special_defence;
			}

		public void setSpecial_defence(int special_defence)
			{
				this.special_defence = special_defence;
			}

		public int getSpecial_attack()
			{
				return special_attack;
			}

		public void setSpecial_attack(int special_attack)
			{
				this.special_attack = special_attack;
			}

		public int getDefence()
			{
				return defence;
			}

		public void setDefence(int defence)
			{
				this.defence = defence;
			}

		public int getAttack()
			{
				return attack;
			}

		public void setAttack(int attack)
			{
				this.attack = attack;
			}

		public int getHp()
			{
				return hp;
			}

		public void setHp(int hp)
			{
				this.hp = hp;
			}

		public String getPrimary_type()
			{
				return primary_type;
			}

		public void setPrimary_type(String primary_type)
			{
				this.primary_type = primary_type;
			}

		public String getSecondary_type()
			{
				return secondary_type;
			}

		public void setSecondary_type(String secondary_type)
			{
				this.secondary_type = secondary_type;
			}
	}
