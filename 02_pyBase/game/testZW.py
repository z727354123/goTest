import time

import win.mouse as mouse

if __name__ == '__main__':
    # saveURL = r"D:\testpyimg\test\haha"
    # saveURL_E = r".png"
    # oldURL = saveURL + "cmp1" + saveURL_E
    # newURL = saveURL + "cmp2" + saveURL_E
    time.sleep(5)
    str = r'What is standard English? Is it spoken in Britain, the US, Canada, Australia, India and New Zealand? Believe it or not, there is no such thing as standard English. Many people believe the English spoken on TV and the radio is standard English. This is because in the early days of radio, those who reported the news were expected to speak excellent English. However, on TV and the radio you will hear differences in the way people speak. When people use words and expressions different form "standard language", it is called a dialect. American English has many dialects, especially the midwestern, southern, African American and Spanish dialects. Even in some parts of the USA, two people from neighbouring towns speak a little differently. American English has so many dialects because people have come from all over the world. Geography also plays a part in making dialects. Some people who live in the mountains of the eastern USA speak with an older kind of English dialect. When Americans moved from one place to another, they took their dialects with them. So people from the mountains in the southeastern USA speak with almost the same dialect as people in the northwestern USA. The USA is a large country in which many different dialects are spoken. Although many Americans move a lot, they still recognize and understand each other'"'"'s dialects. '
    for item in str:
        mouse.keyInputA(item)
    # cap.saveFile(oldURL)
    # cap.saveFile(newURL)
