package com.toft.macromanager;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import com.toft.macromanager.image.ImageSearch;
import com.toft.macromanager.keyboard.KeyboardMacro;
import com.toft.macromanager.mouse.MouseMacro;
import com.toft.macromanager.robot.RobotManager;


public class MainRobot {
	public static void main(String[] args) throws Exception {
		// �⺻ ������ 0.1�ʷ� ����, ���� ���ҰŸ� �ƿ� �Ƚᵵ �������
		RobotManager.getInstance().setAutoDelay(100);
		
		// ���콺��ũ�� ����
		mouseExample();
		
		// Ű�����ũ�� ����
		keyboardExample();
		
		// �̹�����Ī ����
		// �̹��� ��Ī(eclipse.png��� ������ ���, ������ �������� 30, ��Ī���� 100%��Ī)
		imageSearchExample();
	}
	
	public static void mouseExample() {
		// ���콺��ũ�� �ʱ�ȭ
		MouseMacro mouseMacro = new MouseMacro();
		Robot robot = RobotManager.getInstance();
		
		// ���콺 �̵�
		mouseMacro.mouseMove(100, 100);
		
		// ���콺 ���ʹ�ư �������ֱ�
		mouseMacro.mousePress(MouseEvent.BUTTON1_MASK);
		
		// ���콺 ���� ���·� 1�ʰ� ���
		robot.delay(1000);
		
		// ���콺 ���ʹ�ư ����
		mouseMacro.mouseRelease(MouseEvent.BUTTON1_MASK);
		
		// ���콺 ���ʹ�ư Ŭ��
		mouseMacro.mouseClick(MouseEvent.BUTTON1_MASK);
		
		// ���콺 �巡��(from (0, 0) to (100, 100))
		mouseMacro.mouseMove(0, 0);
		mouseMacro.mousePress(MouseEvent.BUTTON1_MASK);
		mouseMacro.mouseMove(100, 100);
		mouseMacro.mouseRelease(MouseEvent.BUTTON1_MASK);
		
		// ���콺 ������ Ŭ��
		mouseMacro.mouseClick(MouseEvent.BUTTON3_MASK);
	}
	
	public static void keyboardExample() {
		// Ű�����ũ�� �ʱ�ȭ
		KeyboardMacro keyboardMacro = new KeyboardMacro();
		Robot robot = RobotManager.getInstance();
		
		// ALT Ű �������ֱ�
		keyboardMacro.keyPress(KeyEvent.VK_ALT);
		
		// ALT+TAB ������
		keyboardMacro.keyClick(KeyEvent.VK_TAB);
		
		// ALT Ű ����
		keyboardMacro.keyRelease(KeyEvent.VK_ALT);
		
		// 1�� ��ٸ���
		robot.delay(1000);
		
		// Ű Ŭ��
		keyboardMacro.keyClick(KeyEvent.VK_ENTER);

	}
	
	public static void imageSearchExample() throws Exception {
		// ���콺 ��ũ�� �ʱ�ȭ
		MouseMacro mouseMacro = new MouseMacro();
		
		// ã�� �̹����� ��ǥ���� ������ ����
		Point imagePoint = null;
		
		try {
			// �̹��� ��Ī(test.png��� ������ ���, ������ �������� RGB�� 30, ������ �̹����� ���������� �����Ͽ� ��ġ�ϴ� ������ 80%��Ī)
			imagePoint = ImageSearch.search("test.png", 30, 80);
		} catch (IOException e) {
			// �Է��� ������ ã�� �� ���� ��� ������ ������
			// TODO �� �κ��� �����ؼ� �ٸ� ������� ó���ص� ��
			throw new Exception("������ ã�� �� �����ϴ�.");
		}
		
		// �̹����� ��ü ȭ�鿡�� �˻����� ������ ���
		if(imagePoint == null) {
			// ��ã�Ҵٰ� ������ ������
			// TODO �� �κ��� �����ؼ� �ٸ� ������� ó���ص� ��
			throw new Exception("�̹����� ã�� �� �����ϴ�.");
		}
		
		// ã�� �̹����� ���콺 �̵�
		mouseMacro.mouseMove(imagePoint);
		
		// �Ʒ��� ���� �ڵ��ص� �������
		try {
			Point localImagePoint = null;
			if((localImagePoint = ImageSearch.search("test.png", 30, 80)) == null) {
				// ����ó��
			}
			
			// ������ ������ ����
			mouseMacro.mouseMove(imagePoint);
		} catch (IOException e) {
			// �Է��� ������ ã�� �� ���� ��� ������ ������
			// TODO �� �κ��� �����ؼ� �ٸ� ������� ó���ص� ��
			throw new Exception("������ ã�� �� �����ϴ�.");
		}
	}
}
