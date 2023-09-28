SELECT NAME FROM STUDENTS WHERE MARKS > 75 ORDER BY SUBSTRING(NAME, -3, 3) ASC, ID ASC;
-- substring(column, start, length)
--nếu start là số âm tức là bắt đầu tại vị trí thứ i từ cuối chuỗi