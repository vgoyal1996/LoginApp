public class LogInThread implements Runnable{
        Context context;
        //Task<AuthResult> task;
        String email,password;

        public LogInThread(Context context,String email,String password) {
            this.context = context;
            this.email = email;
            this.password = password;
            //this.task = task;
        }

        @Override
        public void run() {
            if(TextUtils.isEmpty(email)) {
                Message m = new Message();
                m.what = 2;
                handler.sendMessage(m);
            }
            else if(TextUtils.isEmpty(password)) {
                Message m = new Message();
                m.what = 3;
                handler.sendMessage(m);
            }
            else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            handler.sendEmptyMessage(0);
                            //Toast.makeText(LogInActivity.this,"Wrong EmailId or password",Toast.LENGTH_SHORT).show();
                        } else {
                            handler.sendEmptyMessage(1);
                        }
                    }
                });
            }
        }

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                pd.dismiss();
                switch (msg.what){
                    case 0:
                        Toast.makeText(LogInActivity.this,"Wrong EmailId or password",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //startActivity(new Intent(LogInActivity.this,MainActivity.class));
                        startActivity(new Intent(LogInActivity.this,DrawerActivity.class));
                        finish();
                        break;
                    case 2:
                        Toast.makeText(LogInActivity.this, "Please enter email address!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(LogInActivity.this, "Please enter password!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
    }