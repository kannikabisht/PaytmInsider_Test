use Master

UPDATE Salary SET salary = 5000 
FROM Salary s JOIN Employee e
on s.EmpID = e.EmpID
WHERE DATEDIFF(year,e.Date_of_birth, GETDATE()) > 30