package cn.demo.designer.strategyandsimplefactory;

import cn.demo.designer.strategyandsimplefactory.CasherNormal;
import cn.demo.designer.strategyandsimplefactory.CasherRebate;

public class CasherContext {
	private CasherSuper cs;
	
	public CasherContext(String type){
		switch (type) {
		case "�����շ�":
			cs= new CasherNormal();
		case "������":
			cs=  new CasherRebate("0.5");
		default:
			break;
		}
	}
	
	public double getResult(double money){
		return cs.acceptCaher(money);
	}
}
