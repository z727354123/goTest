import time
import numpy as np
from PIL import ImageGrab


if __name__ == "__main__":
    pass
    img = ImageGrab.grab(bbox=(100, 161, 1141, 610))
    img = np.array(img.getdata(), np.uint8).reshape(img.size[1], img.size[0], 3)




