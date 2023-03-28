drop User if exists pokeshop_user;
create user pokeshop_user identified by 'Dat22bErEnSuperKlasse.';

grant select, insert, update, delete
    on pokedex.*
    to pokeshop_user;

show grants for pokeshop_user;