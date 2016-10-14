# KeywordScaner
This is a Java project, scan a folder and store the keywords into a file.


### Game Rule
---
Since the rules maybe found by other developers, currently, I will leave the rule blank. Anyway, you can find the rule from the project code.
### Why Stringbuffer in Java
---
1. Stringbuffer is more efficiency compared with array or list when consider the big file input.
2. It is synchronized. We are using threads in this project.

However, the down side is making it hard to remove the duplicated keywords. Since we don't have restrictions in the rules, we can ignore it.

### Why Multi-Thread
---
1. We may face many files in the folder and we wish to make full use of the CPU and the memory.
2. Save time.

Here we are using a thread pool to schedule the thread job.

### Why Buffer-Size to Read Files
---
1. We are using threads and there may be big data or big files. Therefor, making it easy to run into a lack of memory. Since we have a limit of buffer size to read the file, we can keep a healthy memory.
2. We can modify the times to write into a file. Only when the buffer is full then the data will put into files.

 

#### Additional Information
  * Remove Duplicated Keywords
   
  We suppose to have the function to remove the duplicated key words. However, since the number of the keys can be very big, to store the key into a list or a array will use up machine memory. We don't consider the detail of this function.
  In REAL project, the keys should be stored in DB instead of files, while set as unique indexed key column, therefore, there is no worry for the memory or performance.
  
DB Table Design :

KEYID(Primary, unique, indexed, int) ;  KEYVALUE(unique, indexed) ; PRIMITIVE(bool)
        
  * For Testing
  
  I didn't spend a lot time in detailed testing. For test result compare, we can create a dynamic keyword comepare function. Also, we need focus on the multi-thread test, to test the performane and the correctness.
   
