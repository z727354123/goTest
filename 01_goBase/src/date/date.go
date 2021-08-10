package date

import "errors"

type Date struct {
	year  int
	month int
	Day   int
}

type book struct {
	BBQ int
}

type Event struct {
	Count   int
	mycount int
	Date
	book
}

func (date *Date) SetYear(year int) error {
	if year < 1 {
		return errors.New("invalid year")
	}
	date.year = year
	return nil
}

func (date *Date) Year() int {
	return date.year
}
func (b *book) DJ() int {
	return 12312
}
func (b *Date) DJ() int {
	return 44444
}
func (b *Event) DJ() int {
	return 5555
}
