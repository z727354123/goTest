import requests

if __name__ == "__main__":
    url="http://my.os/api/notification/charm/"

    headers={'Authorization': 'token 52ee7d4c57686ca8d6884fa4c482a28'}
    payload={'message': "Opportunities and challenges together"}

    r = requests.post(url, headers=headers, data=payload)

    print(r.status_code)
    print("-------------------华丽分割线----------------------")
    print(r.content)