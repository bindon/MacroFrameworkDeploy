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
		// 기본 딜레이 0.1초로 설정, 굳이 안할거면 아예 안써도 상관없음
		RobotManager.getInstance().setAutoDelay(100);
		
		// 마우스매크로 예제
		mouseExample();
		
		// 키보드매크로 예제
		keyboardExample();
		
		// 이미지서칭 예제
		// 이미지 서칭(eclipse.png라는 파일을 사용, 색상의 오차범위 30, 매칭률은 100%매칭)
		imageSearchExample();
	}
	
	public static void mouseExample() {
		// 마우스매크로 초기화
		MouseMacro mouseMacro = new MouseMacro();
		Robot robot = RobotManager.getInstance();
		
		// 마우스 이동
		mouseMacro.mouseMove(100, 100);
		
		// 마우스 왼쪽버튼 누르고있기
		mouseMacro.mousePress(MouseEvent.BUTTON1_MASK);
		
		// 마우스 누른 상태로 1초간 대기
		robot.delay(1000);
		
		// 마우스 왼쪽버튼 떼기
		mouseMacro.mouseRelease(MouseEvent.BUTTON1_MASK);
		
		// 마우스 왼쪽버튼 클릭
		mouseMacro.mouseClick(MouseEvent.BUTTON1_MASK);
		
		// 마우스 드래그(from (0, 0) to (100, 100))
		mouseMacro.mouseMove(0, 0);
		mouseMacro.mousePress(MouseEvent.BUTTON1_MASK);
		mouseMacro.mouseMove(100, 100);
		mouseMacro.mouseRelease(MouseEvent.BUTTON1_MASK);
		
		// 마우스 오른쪽 클릭
		mouseMacro.mouseClick(MouseEvent.BUTTON3_MASK);
	}
	
	public static void keyboardExample() {
		// 키보드매크로 초기화
		KeyboardMacro keyboardMacro = new KeyboardMacro();
		Robot robot = RobotManager.getInstance();
		
		// ALT 키 누르고있기
		keyboardMacro.keyPress(KeyEvent.VK_ALT);
		
		// ALT+TAB 누르기
		keyboardMacro.keyClick(KeyEvent.VK_TAB);
		
		// ALT 키 떼기
		keyboardMacro.keyRelease(KeyEvent.VK_ALT);
		
		// 1초 기다리기
		robot.delay(1000);
		
		// 키 클릭
		keyboardMacro.keyClick(KeyEvent.VK_ENTER);

	}
	
	public static void imageSearchExample() throws Exception {
		// 마우스 매크로 초기화
		MouseMacro mouseMacro = new MouseMacro();
		
		// 찾은 이미지의 좌표값을 저장할 변수
		Point imagePoint = null;
		
		try {
			// 이미지 서칭(test.png라는 파일을 사용, 색상의 오차범위 RGB의 30, 지정한 이미지의 오차범위를 적용하여 일치하는 비율이 80%매칭)
			imagePoint = ImageSearch.search("test.png", 30, 80);
		} catch (IOException e) {
			// 입력한 파일을 찾을 수 없는 경우 강제로 오류냄
			// TODO 이 부분은 수정해서 다른 방법으로 처리해도 됨
			throw new Exception("파일을 찾을 수 없습니다.");
		}
		
		// 이미지를 전체 화면에서 검색하지 못했을 경우
		if(imagePoint == null) {
			// 못찾았다고 강제로 오류냄
			// TODO 이 부분은 수정해서 다른 방법으로 처리해도 됨
			throw new Exception("이미지를 찾을 수 없습니다.");
		}
		
		// 찾은 이미지로 마우스 이동
		mouseMacro.mouseMove(imagePoint);
		
		// 아래와 같이 코딩해도 상관없음
		try {
			Point localImagePoint = null;
			if((localImagePoint = ImageSearch.search("test.png", 30, 80)) == null) {
				// 오류처리
			}
			
			// 오류가 없으면 진행
			mouseMacro.mouseMove(imagePoint);
		} catch (IOException e) {
			// 입력한 파일을 찾을 수 없는 경우 강제로 오류냄
			// TODO 이 부분은 수정해서 다른 방법으로 처리해도 됨
			throw new Exception("파일을 찾을 수 없습니다.");
		}
	}
}
