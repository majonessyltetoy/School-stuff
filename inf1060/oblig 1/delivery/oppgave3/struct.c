typedef unsigned int uint;

int main() {
	return 0;
}

struct datetime {
	// time
	uint hours;
	uint minutes;
	uint seconds;
	// date
	uint year;
	uint month;
	uint date;
};

// Initializes datetime struct
void init_datetime(struct datetime *dt) {
	dt->hours = 00;
	dt->minutes = 0;
	dt->seconds = 0;
	dt->year = 2016;
	dt->month = 1;
	dt->date = 1;
}

/* Set datetime date
 * uint y - year
 * uint m - month
 * uint d - date
 */
void datetime_set_date(struct datetime *dt, uint y, uint m, uint d) {
	dt->year = y;
	dt->month = m;
	dt->date = d;
}

/* Set datetime time
 * uint h   - hours
 * uint min - minutes
 * uint sec - seconds
 */
void datetime_set_time(struct datetime *dt, uint h, uint min, uint sec) {
	dt->hours = h;
	dt->minutes = min;
	dt->seconds = sec;
}

/* Stores the difference between two datetimes in the third datetime
 * datetime *dt_from - from datetime
 * datetime *dt_to   - to datetime
 * datetmie *dt_res  - difference datetime
 */
void datetime_diff(struct datetime *dt_from,
				   struct datetime *dt_to,
				   struct datetime *dt_res) {
	if (dt_to->hours > dt_from->hours)
		dt_res->hours = dt_to->hours - dt_from->hours;
	else
		dt_res->hours = dt_from->hours - dt_to->hours;
	
	if (dt_to->minutes > dt_from->minutes)
		dt_res->minutes = dt_to->minutes - dt_from->minutes;
	else
		dt_res->minutes = dt_from->minutes - dt_to->minutes;
	
	if (dt_to->seconds > dt_from->seconds)
		dt_res->seconds = dt_to->seconds - dt_from->seconds;
	else
		dt_res->seconds = dt_from->seconds - dt_to->seconds;
	
	if (dt_to->year > dt_from->year)
		dt_res->year = dt_to->year - dt_from->year;
	else
		dt_res->year = dt_from->year - dt_to->year;
	
	if (dt_to->month > dt_from->month)
		dt_res->month = dt_to->month - dt_from->month;
	else
		dt_res->month = dt_from->month - dt_to->month;
	
	if (dt_to->date > dt_from->date)
		dt_res->date = dt_to->date - dt_from->date;
	else
		dt_res->date = dt_from->date - dt_to->date;
}
