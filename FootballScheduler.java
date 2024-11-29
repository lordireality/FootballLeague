import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Планировщик игр
 */
public class FootballScheduler {
	
	
	private List<FootballTeam> teams; //Список команд в сезоне
    private List<FootballGame> games; //Список игр в сезоне
	private Boolean isSeasonOver = false; //Флаг - закончен ли сезон
	private int winterCounter = 0; //Счетчик колличества
    
	/*
	 * Возвращает значение isSeasonOver флага окончания сезона
	 */
	public Boolean isSeasonOver() { 
		return this.isSeasonOver;
	}
	
	/*
	 * Класс конструктор планировщика игр
	 */
    public FootballScheduler(List<FootballTeam> teams) { 
        this.teams = teams;
        this.games = new ArrayList<>();
    }
    /**
     * Получить список игр сезона из планировщика
     * @return
     */
    public List<FootballGame> getGames(){
    	return this.games;
    }
    /**
     * Получить список команд сезона из планировщика
     * @return
     */
    public List<FootballTeam> getTeams(){
    	return this.teams;
    }
    
    //Проверка на конец сезона
    public void seasonOverCheck() {
    	this.isSeasonOver = this.winterCounter >= 3;
    }
    
    /*
     * Запланировать игру на определенную игру
     * Возвращает кортеж, где
     * X - Boolean в зависимости от успешности проведения игры 
     * Y - Информационное сообщение к Boolean
     */
    public Tuple<Boolean, String> scheduleGame(int temperature) {
    	
    	//Если температура меньше 0, не проводим игру +
    	//делаем инкремент для счетчика зимы (если более 3-х недель меньше нуля, значит конец сезона)
    	if(temperature <= 0) {
    		this.winterCounter ++;
    		seasonOverCheck(); //Проверка на завершение сезона
    		return new Tuple(false,"Слишком холодно для игры");
    	}
    	this.winterCounter = 0; //обнуляем счетчик если более 0 градусов
    	Random random = new Random();
    	
    	List<FootballTeam> alreadyPlayedTeams = new ArrayList<FootballTeam>(); //кэш список уже сыгравших команд
    	
    	//Вычисляем сколько будет игр по принципу кол-во участников/2.
    	// минус такого подхода, что если у нас не четное кол-во команд, получим StackOverflow.
    	// как вариант, сделать условие, что если у нас есть такая "лишняя команда", тогда просто взять по какому то критерию соперника
    	for(int i = 0; i < teams.size()/2; i++) {
    		FootballTeam homeTeam = null;
    		
    		//генерируем рандомную команду, которой нет в списке использованных.
    		//Null Нужен для старта цикла
    		//Минус подхода - мы не исключаем что несколько итераций будет рандомно попадаться
    		//одна и та же команда использованная ранее
    		//При большом объеме команд, будет высокий TimeComplexity этого участка кода.
    		//Если бы я это делал на C# я бы использовал Linq с конструкцией Where либо Except 
    		
    		while(homeTeam == null || alreadyPlayedTeams.contains(homeTeam)) { 
    			homeTeam = teams.get(random.nextInt(teams.size()));
    		}
    		alreadyPlayedTeams.add(homeTeam);
    		FootballTeam awayTeam = null;
    		while(awayTeam == null || alreadyPlayedTeams.contains(awayTeam)) {
    			awayTeam = teams.get(random.nextInt(teams.size()));
    		}
    		alreadyPlayedTeams.add(awayTeam);
    		
        	//Генерируем случайные очки.
    		//так как по условию чем больше температура тем больше голов
    		//я вывел следующую формулу - МаксГолов = 5 + (температура/10)
    		int maxGoals = 5+ (int) Math.round(temperature/10);
            int awayScore = random.nextInt(maxGoals);
            int homeScore = random.nextInt(maxGoals);
        
            //запись кол-ва забитых и пропущенных у команды
            awayTeam.addPoints(awayScore, homeScore);
            homeTeam.addPoints(homeScore, awayScore);
            
            //Поиск победетеля. Запись в статистику команды
            if(awayScore > homeScore) {
            	awayTeam.addWin();
            	homeTeam.addLoose();
            } else if (awayScore <= homeScore) {
            	homeTeam.addWin();
            	awayTeam.addLoose();
            } else {
            	homeTeam.addTie();
            	awayTeam.addTie();
            }
            
            //Добавляем в список проведенных игр
            games.add(new FootballGame(awayTeam.toString(), homeTeam.toString(), awayScore, homeScore, temperature));
    		
    	}
        
        return new Tuple(true,"Игра состоялась"); //текст не несет информативности, т.к. я его не вывожу нигде, но пускай будет
    	
    }
    
    
}
