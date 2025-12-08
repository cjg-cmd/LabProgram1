package racetrack;

import java.util.ArrayList;

public class Path {
	
	private int x0;
	private int x1;
	
	private int y0;
	private int y1;
	
	private Racetrack track;
	private boolean visible;
	private char id;
	
	public ArrayList<Position> poses = new ArrayList<>();
 	
	public Path(int x0, int y0, int x1, int y1, Racetrack track)
	{
		this.x0 = x0;
		this.x1 = x1;
		
		this.y0 = y0;
		this.y1 = y1;
		
		id = '0';
		visible = false;
		this.track = track;
	}
	
	private void octant0(int x, int y, int dx, int dy, int x_dir, int y_dir)
	{
		int dy2 = dy * 2;
		int dy2_minus_dx2 = dy2 - (dx * 2);
		int error = dy2 - dx;

		check(x, y);

		while (dx > 0)
		{
			if (error >= 0)
			{
				y += y_dir;
				error += dy2_minus_dx2;
			}
			else
			{
				error += dy2;
			}

			x += x_dir;
			check(x, y);

			dx--;
		}
	}
	
	private void octant1(int x, int y, int dx, int dy, int x_dir, int y_dir)
	{
		int dx2 = dx * 2;
		int dx2_minus_dy2 = dx2 - (dy * 2);
		int error = dx2 - dy;

		check(x, y);

		while (dy > 0)
		{
			if (error >= 0)
			{
				x += x_dir;
				error += dx2_minus_dy2;
			}
			else
			{
				error += dx2;
			}

			y += y_dir;
			check(x, y);

			dy--;
		}
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public void setID(char id)
	{
		this.id = id;
	}
	
	public void GetResults()
	{
		int dx = x1 - x0;
		int dy = y1 - y0;

		int abs_dx = Math.abs(dx);
		int abs_dy = Math.abs(dy);

		int x_dir = (dx >= 0) ? 1 : -1;
		int y_dir = (dy >= 0) ? 1 : -1;

		// Always start from original x0,y0
		if (abs_dx > abs_dy)
		{
			// low slope
			octant0(x0, y0, abs_dx, abs_dy, x_dir, y_dir);
		}
		else
		{
			// steep slope
			octant1(x0, y0, abs_dx, abs_dy, x_dir, y_dir);
		}
	}
	
	private void check(int x, int y)
	{
		if (x < 0 || x >= track.width()) return;
		if (y < 0 || y >= track.height()) return;
		
		poses.add(new Position(y, x));
		
		if (visible)
		{
			track.setCar(y, x, id);
		}
	}
}
