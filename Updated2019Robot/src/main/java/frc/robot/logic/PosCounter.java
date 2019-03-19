package frc.robot.logic;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PosCounter implements PIDSource {
	
	private int endPoint;
	private int curPosition;
	private int itrNum;
	private int lastItrPos;
	private int lastItrNeg;
	private int gap;
	
	public PosCounter(int gap) {
		this.gap = gap;
		itrNum = 0;
		endPoint = 0;
		lastItrPos = -gap - 1;
		lastItrNeg = -gap - 1;
	}
	
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double pidGet() {
		double output;
		itrNum++;
		if (curPosition < endPoint) {
			if (itrNum - lastItrNeg >= gap) {
				curPosition++;
				lastItrPos = itrNum;
				output = 1;
			}
			else {
				output = 0;
			}
		}
		else if (curPosition == endPoint) {
			output = 0;
		}
		else {
			if (itrNum - lastItrPos >= gap) {
				curPosition--;
				lastItrNeg = itrNum;
				output = -1;
			}
			else {
				output = 0;
			}
		}
		SmartDashboard.putString("posCounter Output",String.format("%f", output));
		return output;
	}
}