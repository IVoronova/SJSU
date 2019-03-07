
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <string.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>

pid_t id;
FILE *fp;
char* command;
char* arg;
char fileName[100];

//Only interupts child
static void killChild(int sig, siginfo_t *si, void *ignore){
    printf("Signaling %d\n", id);
    fp = fopen(fileName, "a");
    fprintf(fp,"Stopped executing %s %s due to signal = %d\n", command, arg, SIGINT);
    fclose(fp);
    //interupts child
    kill(id,SIGINT);


}

//Interupts child and exits
static void killParent(int sig, siginfo_t *si, void *ignore){
    printf("Signaling %d \nExiting due to quit signal\n", getpid());
    //interupts child
    kill(id, SIGINT);
    exit(0);
}

int main(int argc, char *argv []){
  
struct sigaction sa1; //signal to terminate parent
struct sigaction sa2; //signal to terminate child
sa1.sa_flags = SA_SIGINFO;
sigemptyset(&sa1.sa_mask);
sa1.sa_sigaction = killParent;
sa2.sa_flags = SA_SIGINFO;
sigemptyset(&sa2.sa_mask);
sa2.sa_sigaction = killChild;

if (sigaction(SIGQUIT, &sa1, NULL) == -1){
  perror("sigaction SIGQUIT");
  exit(errno);
}
if (sigaction(SIGINT, &sa2, NULL) == -1){
  perror("sigaction SIGINT");
  exit(errno);
}


char * endHalf = ".out";
int fd;
command = argv[1]; //command passed to forall

//going through all the arguments
for(int i = 2; i < argc ; i++){
  arg = argv[i]; // argument for the command
  sprintf(fileName, "%d", i - 1);
  strcat(fileName, endHalf);
  fp = fopen(fileName, "w");
  fd = open(fileName, O_RDWR | O_CREAT | O_APPEND, S_IRUSR |S_IWUSR);

  fprintf(fp, "Executing %s %s\n", command, arg);
  fclose(fp);
  id = fork();

  //child
  if(id == 0){
    //redirects stdout to file
    if(-1 == dup2(fd, 1))
      perror("Failed to dup stdout");
    if(-1 == dup2(fd, 2))
      perror("Failed to dup stderr");
    close(fd);

    //calls new process
    execlp(command, command, arg, NULL);
    perror("Fail to exec");
    exit(errno);

  }
  int status;
  //waits for the child to finish
  waitpid(id, &status,WUNTRACED);

  //if child was not inturrupted
  if(!WIFSIGNALED(status)){
    fp = fopen(fileName, "a");
    fprintf(fp,"Finished executing %s %s exit code = 0\n", command , arg);
    fclose(fp);
  }

  }
  return 1;
}
