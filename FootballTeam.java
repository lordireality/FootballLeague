
/**
 * Футбольная команда
 */
public class FootballTeam {
	/**
	 * Наименование ФК
	 */
	private String name = "empty";
	/**
	 * Кол-во побед ФК
	 */
	private int winsCount = 0;
	/**
	 * Кол-во поражений ФК
	 */
	private int looseCount = 0;
	/**
	 * Кол-во ничей ФК 
	 */
	//(p.s. в задании указано как "общее число дополнительных матчей", некорректный перевод)
	private int tiesCount = 0;
	/**
	 * забитые голы
	 */
	private int pointsScored = 0;
	/**
	 * Кол-во пропущенных голов
	 */
	private int pointsAllowed = 0;
	
	/**
	 * Конструктор класса футбольной команды
	 * @param name
	 */
	FootballTeam(String name){
		this.name = name;
	}
	
	/**
	 * Добавить очки
	 * @param scored
	 * @param allowed
	 */
	public void addPoints(int scored, int allowed) {
		this.pointsScored += scored;
		this.pointsAllowed += allowed;
	}
	
	
	/*
	 * Добавить победу
	 */
	public void addWin() {
		this.winsCount++;
	}
	/**
	 * Добавить поражение
	 */
	public void addLoose() {
		this.looseCount++;
	}
	/*
	 * Добавить ничью
	 */
	public void addTie() {
		this.tiesCount++;
	}
	
	/**
	 * Переопределение .toString для вывода исключительно наименования
	 */
	@Override
    public String toString() {
		return this.name;
	}
	
	/*
	 * Вывести результаты команды
	 */
    public String getResults() {
        return this.name + "\r\n"
        		+ "Побед: "+this.winsCount+", Поражений: "+this.looseCount+", Ничья: "+this.tiesCount+"\r\n"
        		+ "Забито голов: "+this.pointsScored+", Пропущено голов: "+this.pointsAllowed;
    }
	
}
