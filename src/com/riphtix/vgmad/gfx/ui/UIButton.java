package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.handler.Mouse;
import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UIButton extends UIComponent {

	protected UILabel label;
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;

	private Image image;

	private boolean mouseInButtonBounds = false;
	private boolean buttonPressed = false;
	private boolean ignorePressed = false;
	private boolean ignoreAction = false;

	public boolean dropShadow = false;
	public int dropShadowOffset = 2;

	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
		super(position, size);
		this.actionListener = actionListener;
		Vector2i labelPosition = new Vector2i(position);
		labelPosition.x += 4;
		labelPosition.y += size.y - 7;
		label = new UILabel(labelPosition, "");
		label.setColor(0xffa0a0a0);
		label.active = false;

		init();
	}

	public UIButton(Vector2i position, Image image, UIActionListener actionListener){
		super(position, new Vector2i(image.getWidth(null), image.getHeight(null)));
		this.actionListener = actionListener;
		setImage(image);
		init();
	}

	private void init(){
		color = new Color(0xff6a6a6a);
		buttonListener = new UIButtonListener();
	}

	void init(UIPanel panel) {
		super.init(panel);
		if(label != null) {
			panel.addComponent(label);
		}
	}

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	public void setText(String text) {
		if (text == "")
			label.active = false;
		else
			label.text = text;
	}

	public void performAction() {
		actionListener.performAction();
	}

	public void ignoreNextPress(){
		ignoreAction = true;
	}

	public void setFont(Font font) {
		label.setFont(font);
	}

	public void setDropShadow(boolean shadow, int offset) {
		label.dropShadow = shadow;
		label.dropShadowOffset = offset;
	}

	public void tick() {//public void update()
		Rectangle buttonBounds = new Rectangle(getAbsolutePosition().x, getAbsolutePosition().y, size.x, size.y);
		boolean leftMouseButtonPressed = Mouse.getButton() == MouseEvent.BUTTON1;
		boolean noButtonPressed = Mouse.getButton() == MouseEvent.NOBUTTON;
		if (buttonBounds.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if (!mouseInButtonBounds) {
				if (leftMouseButtonPressed) {
					ignorePressed = true;
				} else
					ignorePressed = false;
				buttonListener.mouseEnteredButtonBounds(this);
			}
			mouseInButtonBounds = true;

			if (!buttonPressed && !ignorePressed && leftMouseButtonPressed) {
				buttonListener.buttonPressed(this);
				buttonPressed = true;
			} else if (noButtonPressed) {
				if (buttonPressed) {
					buttonListener.buttonReleased(this);
					if (!ignoreAction) {
						actionListener.performAction();
					} else
						ignoreAction = false;
					buttonPressed = false;
				}
				ignorePressed = false;
			}
		} else {
			if (mouseInButtonBounds) {
				buttonListener.mouseExitedButtonBounds(this);
				buttonPressed = false;
			}
			mouseInButtonBounds = false;
		}
	}

	public void render(Graphics g) {
		int x = position.x + offset.x;
		int y = position.y + offset.y;

		if(image != null){
			if (dropShadow) {
				g.setColor(Color.BLACK);
				g.fillRect(x + dropShadowOffset, y + dropShadowOffset, size.x, size.y);
			}
			g.drawImage(image, x, y, null);
		}else {
			if (dropShadow) {
				g.setColor(Color.BLACK);
				g.fillRect(x + dropShadowOffset, y + dropShadowOffset, size.x, size.y);
			}
			g.setColor(color);
			g.fillRect(x, y, size.x, size.y);

			if (label != null) {
				label.render(g);
			}
		}
	}
}
