package modelTest;



import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Board;
import model.BoardConfiguration;
import model.Ghost;
import model.Pacman;

public class CreatureTest {
	Board board;
	Ghost ghost;
	Pacman pacman;

	@BeforeClass
	public void before() {
		pacman = new Pacman();
		BoardConfiguration boardConfiguration = new BoardConfiguration();
		ghost = new Ghost();
		board = new Board(boardConfiguration.level1Board);
	}

	@Test
	public void death() {
		assertEquals(pacman.isDead(), false);

		assertEquals(ghost.isDead(), false);
		ghost.kill();
		assertEquals(ghost.isDead(), true);
	}

	public void move() {
		assertEquals(pacman.getPosition().isNavegable(pacman), true);
		board.move(pacman);
		assertEquals(pacman.getPosition().isNavegable(pacman), true);

	}
}
