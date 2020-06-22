use Master

--Replace @rownum with the desired nth largest salary value

WITH CustomSalary AS 
(SELECT *,
rownum = ROW_NUMBER() OVER (ORDER BY salary DESC) FROM Salary) 
SELECT cs.*, e.EmpName FROM CustomSalary cs 
JOIN Employee e on e.EmpID = cs.EmpID WHERE rownum = 3