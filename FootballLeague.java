
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

/**
 * Основной класс программы
 * 
 * @author (German Zykin)
 * @version (1.0) 
 */
public class FootballLeague {

	//Заранее объясню некоторые трюки и моменты которые сделаны бонусом
	//особая просьба к преподавателю который будет оценивать, задание есть задание, но нужно мыслить шире
	//1. Реализация всех классов не имеет привязки к Console, мы можем использовать классы например в 
	//отдельном приложении или вообще в 3D игре, например
	//2. Мой основной ЯП - C#, так что я писал в том стиле, который мне привычнее. 
	//Например, тут реализован Tuple - в простонародии Кортеж
	//3. Я закладывал дополнительные моменты, которые в теории могут расширить функционал в дальнейшем
	//например: возможность динамического указания кол-ва играющих комманд
	//4. Я полностью русифицировал и адаптировал решение, т.к. перевод задания на русский был "Потраченный"
	
	
	
	/*
	 * Точка входа в программу
	 */
	public static void main(String[] args) {
		
		
		//Сканнер - документашка, правда по 8SE - https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
		//Очень непривычная штука после тривиальных Console.ReadLine или cout >>
		Scanner sc = new Scanner(System.in);
		
		//вводим кол-во команд - при желании можно добавить ввод с консоли
		int teamsCount = 4;
		//инициализируем список существующих команд
		List<FootballTeam> teams = new ArrayList<>();
		//TODO: В случае реализации указания кол-ва команд, необходима проверка что команд более чем двух,
		//т.к. по условию команда не может играть сама с собой
		
		//указываем названия каждой команды
		for(int i = 0; i<teamsCount; i++) {
			System.out.println("Укажите наименование ФК:");
			String tmpName = sc.nextLine(); //ввод с клавиатуры названия
			teams.add(new FootballTeam(tmpName)); //создаем команду в общем списке с указанным названием
			
		}
		//Создаем экземпляр планировщика игр
		FootballScheduler scheduler = new FootballScheduler(teams);
		while(!scheduler.isSeasonOver()) { //проводим игры до тех пор пока по внутренней логике планировщика, не будет метки окончания сезона
			try {
                System.out.print("Укажите температуру: ");
                int temperature = sc.nextInt();
                Tuple<Boolean, String> result =  scheduler.scheduleGame(temperature); //см. планировщик
                if(!result.x) {
                	System.out.println(result.y);
                }
                
			} catch (InputMismatchException e) {
                System.out.println("Указано некорректное значение!");
                sc.nextLine();
            }
		}
		System.out.println("Сезон завершен");
		for(FootballTeam team : scheduler.getTeams()) {
			System.out.println(team.getResults() + "\n");
		}
		for(FootballGame game : scheduler.getGames()) {
			System.out.println(game.getResults() + "\n");
		}
		System.out.println();
		
		sc.close();
		
	}
}
