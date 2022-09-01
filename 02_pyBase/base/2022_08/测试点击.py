#!/usr/bin/python
'''
Created on 2013-8-30
@author: maricoliu
'''
import sys
import time
from Quartz.CoreGraphics import *
import Quartz as q


def _mouseEvent(type, posx, posy):
	theEvent = CGEventCreateMouseEvent(None, type, (posx, posy), kCGMouseButtonLeft)
	CGEventPost(kCGHIDEventTap, theEvent)


def mouseMove(posx, posy):
	_mouseEvent(kCGEventMouseMoved, posx, posy)


def mouseClickDown(posx, posy):
	_mouseEvent(kCGEventLeftMouseDown, posx, posy)


def mouseClickUp(posx, posy):
	_mouseEvent(kCGEventLeftMouseUp, posx, posy)


def mouseDrag(posx, posy):
	_mouseEvent(kCGEventLeftMouseDragged, posx, posy)


def mouseClick(posx, posy):
	'''perform a left click'''
	_mouseEvent(kCGEventLeftMouseDown, posx, posy)
	_mouseEvent(kCGEventLeftMouseUp, posx, posy)


def mouseRightClick(posx, posy):
	theEvent = CGEventCreateMouseEvent(None, kCGEventRightMouseDown, (posx, posy), kCGMouseButtonRight)
	CGEventPost(kCGHIDEventTap, theEvent)
	theEvent2 = CGEventCreateMouseEvent(None, kCGEventRightMouseUp, (posx, posy), kCGMouseButtonRight)
	CGEventPost(kCGHIDEventTap, theEvent2)


def mouseDoubleClick(posx, posy):
	'''perfrom a double left click'''
	theEvent = CGEventCreateMouseEvent(None, kCGEventLeftMouseDown, (posx, posy), kCGMouseButtonLeft);
	CGEventPost(kCGHIDEventTap, theEvent);
	CGEventSetType(theEvent, kCGEventLeftMouseUp);
	CGEventPost(kCGHIDEventTap, theEvent);
	CGEventSetIntegerValueField(theEvent, kCGMouseEventClickState, 2);
	CGEventSetType(theEvent, kCGEventLeftMouseDown);
	CGEventPost(kCGHIDEventTap, theEvent);
	CGEventSetType(theEvent, kCGEventLeftMouseUp);
	CGEventPost(kCGHIDEventTap, theEvent);


def mouseScroll(movement=30, direction=1):
	'''
	@param movement: lines to scroll, Integer
	@param direction: scroll up or scroll down, 1:scroll up, -1:scroll down
	'''
	for i in range(movement):
		theEvent = CGEventCreateScrollWheelEvent(None, kCGScrollEventUnitLine, 1, direction)
		CGEventPost(kCGHIDEventTap, theEvent)
		time.sleep(0.02)
	# CGPostScrollWheelEvent(1, 5)


if __name__ == '__main__':
	location = q.NSEvent.mouseLocation()
	print(location.x)
	print(location.y)
	time.sleep(2)
	mouseClickDown(767, 390)
	mouseClickUp(767, 390)
	for i in range(1000000):
		if i > 100 :
			x = q.NSEvent.mouseLocation().x
			if not 766 <= x <= 768:
				break
		time.sleep(0.0008)
		mouseClickDown(767, 390)
		mouseClickUp(767, 390)
	time.sleep(0.0007)
	mouseMove(1096, 390)
# mouseMove(int(currentpos.x),int(currentpos.y)); # Restore mouse position

