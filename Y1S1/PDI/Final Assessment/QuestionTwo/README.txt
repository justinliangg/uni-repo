/*-------------------------------
  FILE: README.txt
  Author: Justin Liang (19821986)
  UNIT: PDI
--------------------------------*/

//WeatherData class

There was no information on how the class fields for WeatherData class were supposed to be validated,
so I have come up with my own validations for each class fields and why I have chosen them.

- For code and stationNumber
Only validation for both of these class fields were if they were empty or null
This is because they were strings so I assumed no validation was required.

- For year
Since year is an integer, I have validated so it can only be above 1600
The validation number was a little arbitrary.
but I also think that there is probably no weather data below that year.

-For Month
Have validated month to be between 1 to 12

-For Day
Validation for day was a little more complicated,

1) Had to check the month, different months have different max days
2) If month was february, had to check whether it was a leap year to get max number of days
in the month.

-For maxTemp
Validation for maxTemp was between -100 and 70.
The reason for those numbers is because the highest and lowest temperature recorded on earth
is between -88 and 58 degrees. I highly doubt any temperature records will be broken in Australia, but
just in case I gave a bit of margin for the max and min temp.


//AssessmentMain Class

-Omitting a row
In the specifications, it only says to omit an empty row, but made no mention about
invalid values in that row.

So I have also added that it will omit a row if the values in that row are 
outside the required values used to construct a WeatherData object.

-Month Input
There was no specification for what input the user should give for month (As a number or a string). 
So I have allowed user to enter both, Example. january or 1 will be accepted for the month January.

-Min Temperature
With the change in specification from Dr McMeekin's email, I have completed omited anything about Min Temperature

