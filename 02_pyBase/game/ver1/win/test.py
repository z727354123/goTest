# -*- coding: utf-8 -*-


import time

import win32api
import win32con
import win32gui

import win.jinshandazi as dz

if __name__ == '__main__':

    s = "What is standard English? Is it spoken in Britain, the US, Canada, Australia, India and New Zealand? Believe it or not, there is no such thing as standard English. Many people believe the English spoken on TV and the radio is standard English. This is because in the early days of radio, those who reported the news were expected to speak excellent English. However, on TV and the radio you will hear differences in the way people speak.When people use words and expressions different form \"standard language\", it is called a dialect. American English has many dialects, especially the midwestern, southern, African American and Spanish dialects. Even in some parts of the USA, two people from neighbouring towns speak a little differently. American English has so many dialects because people have come from all over the world. Geography also plays a part in making dialects. Some people who live in the mountains of the eastern USA speak with an older kind of English dialect. When Americans moved from one place to another, they took their dialects with them. So people from the mountains in the southeastern USA speak with almost the same dialect as people in the northwestern USA. The USA is a large country in which many different dialects are spoken. Although many Americans move a lot, they still recognize and understand each other's dialects"
    time.sleep(3)
    def send_str(text, hwnd=None):
        astrToint = [ord(c) for c in text]
        window = win32gui.GetForegroundWindow()
        for item in astrToint:
            time.sleep(0.40)
            win32api.PostMessage(window, win32con.WM_CHAR, item, 0)
    send_str(s)
